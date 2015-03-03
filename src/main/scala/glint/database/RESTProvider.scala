package glint.database
import java.nio.charset.Charset
import com.ning.http.client.Response
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import dispatch.{host,Req,Http,url}
import com.sun.jersey.core.util.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Provide DB access through REST API
 *  @param dbURL - Database URL
 *  @param auth - Some((< user >,< password >)) or None
 *  @param executor - an ExecutionContext
 * Created by CAB on 03.03.2015.
 */

class RESTProvider(
  dbURL:String = "localhost:7474",
  auth:Option[(String,String)] = None,
  logger:Logger = LoggerFactory.getLogger("glint.database.RESTProvider"))
  (implicit executor: ExecutionContext)
extends Provider {
  //Parameters
  val requestTimeout = Duration(10, "second")
  val charEncoding = "UTF-8"
  val contentType = "application/json"
  val xStream = "true"
  val urlPrefix = "http://"
  //Helpers
  private val authHeader = auth.map{
    case (user,password) ⇒ "Basic " + new String(Base64.encode( user + ":" + password ), Charset.forName( "UTF-8" ))}
  private val baseRequest = {
    val req = host(dbURL.stripPrefix(urlPrefix)).setBodyEncoding(charEncoding)
      .addHeader("Accept",contentType)
      .addHeader("X-Stream",xStream)
    authHeader match{
      case Some(authStr) ⇒ req.addHeader("Authorization", authStr)
      case _ ⇒ req}}
  //Functions
  private def requestToString(req:Req):String =
    s"request{url = ${req.url}, props ${req.props}}"
  private def responseToString(res:Response):String =
    s"response{status code = ${res.getStatusCode}, header = ${res.getHeaders}, body = ${res.getResponseBody}}"
  private def makeRequest(request:Req):Future[Response] = {
    Http(request)
      //Re request on 302 (found)
      .flatMap{
        case res if res.getStatusCode == 302 ⇒ Http(url(res.getHeader("Location"))
          .setBodyEncoding(charEncoding)
          .addHeader("Accept",contentType)
          .addHeader("X-Stream",xStream))
        case res ⇒ Future.successful(res)}
      //Check result
      .map{
        case res if res.getStatusCode == 200 ⇒ res
        case res if res.getStatusCode == 401 ⇒  throw new DatabaseException(
          "No authorization, " + responseToString(res))
        case res ⇒ throw new DatabaseException(
          s"Fail on http, ${requestToString(request)};  ${responseToString(res)}")}}
  //Get service root
  logger.debug(s"Start with URL: $dbURL, Authorization: ${auth.map{case(u,_) ⇒ u + ":*****"}}.")
  val (neo4jVersion, cypherPoint, transactionPoint) = {
    import org.json4s._
    import org.json4s.native.JsonMethods._
    implicit val formats = DefaultFormats
    val res = Await.result(makeRequest(baseRequest / "db" / "data" / ""), requestTimeout)
    val json = parse(res.getResponseBody)
    ((json \ "neo4j_version").extract[String],
     (json \ "cypher").extract[String],
     (json \ "transaction").extract[String])}
  logger.debug(s"Neo4j version: $neo4jVersion. Obtained points: $cypherPoint, $transactionPoint")
  //Override methods
  override def toString:String = s"glint.database.RESTProvider(url = $dbURL)"
  //Methods
  def executeCypherWithRawResult(cypher:String):Future[String] = {










    Future.successful("----")
  }

}
