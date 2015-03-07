package glint.database
import java.nio.charset.Charset
import java.util.concurrent.ExecutionException
import com.ning.http.client.Response
import org.json4s.JsonAST.JValue
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import dispatch.{host,Req,Http,url}
import com.sun.jersey.core.util.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.json4s.native.Serialization.{read, write}
import org.json4s.DefaultFormats
import scala.concurrent.duration._


/**
 * Provide DB access through REST API
 *  @param dbURL - Database URL
 *  @param auth - Some((< user >,< password >)) or None
 *  @param logger - Module logger
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
  val requestTimeout = 10.second
  val charEncoding = "UTF-8"
  val contentType = "application/json"
  val xStream = "true"
  //Helpers
  private val authHeader = auth.map{
    case (user,password) ⇒ "Basic " + new String(Base64.encode( user + ":" + password ), Charset.forName( "UTF-8" ))}
  private implicit val jsonFormats = DefaultFormats
  //Functions
  private def constructRequest(endPointUrl:String, body:Option[String] = None, method:String = "POST"):Req = {
    val initReq = url(endPointUrl)
      .setBodyEncoding(charEncoding)
      .addHeader("Accept",contentType)
      .addHeader("Content-Type",contentType)
      .addHeader("X-Stream",xStream)
      .setMethod(method)
    val authReq = authHeader.fold(initReq)(a ⇒ initReq.addHeader("Authorization", a))
    body.fold(authReq)(b ⇒ authReq.setBody(b))}
  private def requestToString(request:Req):String = {
    val req = request.toRequest
    s"request{url = ${req.getUrl}, headers ${req.getHeaders}, body = ${req.getStringData}}"}
  private def responseToString(res:Response):String =
    s"response{status code = ${res.getStatusCode}, header = ${res.getHeaders}, body = ${res.getResponseBody}}"
  private def makeRequest(request:Req):Future[Response] = {
    Http(request)
      //Re request on 302 (found)
      .flatMap{
        case res if res.getStatusCode == 302 ⇒ {
          logger.warn("Re request by 302 on: " + requestToString(request))
          val req = request.toRequest
          Http(constructRequest(res.getHeader("Location"), Some(req.getStringData), req.getMethod))}
        case res ⇒ Future.successful(res)}
      //Check result
      .map{
        case res if res.getStatusCode == 200 ⇒ {
          logger.trace("Data " + responseToString(res))
          res}
        case res if res.getStatusCode == 400 ⇒ {
          val msg = s"""
            |Bad Request(400):
            |  request body = ${request.toRequest.getStringData}
            |  response body = ${res.getResponseBody}
             """.stripMargin
          logger.error(msg)
          throw new DatabaseHTTPException(msg, res.getStatusCode, request, Some(res))}
        case res if res.getStatusCode == 401 ⇒ {
          val msg = "No authorization:\n  " + responseToString(res)
          logger.error(msg)
          throw new DatabaseHTTPException(msg, res.getStatusCode, request, Some(res))}
        case res ⇒ {
          val msg = s"Fail on http:\n  ${requestToString(request)}\n  ${responseToString(res)}"
          logger.error(msg)
          throw new DatabaseHTTPException(msg, res.getStatusCode, request, Some(res))}}}
  //Get service root
  logger.debug(s"Start with URL: $dbURL, Authorization: ${auth.map{case(u,_) ⇒ u + ":*****"}}.")
  val (neo4jVersion, cypherPoint, transactionPoint) = {
    import org.json4s._
    import org.json4s.native.JsonMethods._
    //Preparing URL
    val url = "http://" + dbURL.stripPrefix("http://").stripPrefix("https://").stripSuffix("/") + "/db/data/"
    //Get JSON
    val res =
      try{
        Await.result(makeRequest(constructRequest(url, None, "GET")), requestTimeout).getResponseBody}
      catch{
        case e:ExecutionException ⇒ {
          logger.error("Exception on http request when getting service root: " + e.getCause)
          throw e.getCause}}
    //Get values
    try{
      val json = parse(res)
      ((json \ "neo4j_version").extract[String],
       (json \ "cypher").extract[String],
       (json \ "transaction").extract[String])}
    catch{case e:MappingException ⇒
      logger.error("Exception on extract JSON values when getting service root: " + e.getCause)
      throw new DatabaseException("Fail on get service root on parsing JSON: " + res)}
    }
  logger.debug(s"Neo4j version: $neo4jVersion. Obtained points: $cypherPoint, $transactionPoint")
  //Override methods
  override def toString:String = s"glint.database.RESTProvider(url = $dbURL)"
  //Methods
  def executeCypherWithRawResult(cypher:String, params:Map[String,Any]):Future[String] = {
    logger.trace(s"Called executeCypherWithRawResult(cypher = $cypher, params = $params)")
    //Create JSON
    val json = write(Map("query" → cypher, "params" → params))
    //Make request
    makeRequest(constructRequest(cypherPoint, Some(json))).map(res ⇒ {
      logger.trace("In executeCypherWithRawResult obtained: " + responseToString(res))
      res.getResponseBody})}
  def executeCypherWithJSONResult(cypher:String, params:Map[String,Any]):Future[JValue] = {
    import org.json4s.native.JsonMethods._
    logger.trace(s"Called executeCypherWithJSONResult(cypher = $cypher, params = $params)")
    //Create JSON
    val json = write(Map("query" → cypher, "params" → params))
    //Make request
    makeRequest(constructRequest(cypherPoint, Some(json))).map(res ⇒ {
      logger.trace("In executeCypherWithJSONResult obtained: " + responseToString(res))
      parse(res.getResponseBody)})}}
