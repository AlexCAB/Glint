package glint.database
import helpers.{FuturesHelpers, DummyLogger}
import org.json4s._
import org.json4s.native.JsonMethods._
import org.specs2.mutable.Specification
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Test for RESTProvider classclean
 * Created by CAB on 04.03.2015.
 */

class RESTProviderTest extends Specification with FuturesHelpers{
  //Parameters
  val url = "localhost:7474"
  val userPassword = Some("neo4j","1234")
  //Helpers
  private implicit val jsonFormats = DefaultFormats
  //Preparing
  System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO")
  //Testing
  "RESTProvider" should {
    "connect to DB" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      //Running
      val provider = new RESTProvider(dbURL = url, auth = userPassword, logger)
      //Checking
      provider.cypherPoint mustEqual "http://localhost:7474/db/data/cypher"
      logger.allCounts mustEqual (1,2,0,0,0)}
    "not connect to DB with wrong URL, user name or password" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      //Checking
      new RESTProvider(dbURL = "-----:7474", auth = userPassword, logger) must throwA[java.net.ConnectException]
      new RESTProvider(dbURL = url, auth = Some("---","1234"), logger) must throwA[DatabaseHTTPException]
      new RESTProvider(dbURL = url, auth = Some("neo4j","---"), logger) must throwA[DatabaseHTTPException]
      logger.allCounts mustEqual (0,3,0,0,3)}
    "execute Cypher with raw result" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      val provider = new RESTProvider(dbURL = url, auth = userPassword, logger)
      //Running
      val res = provider.executeCypherWithRawResult("create (n:N{a:123,b:'srt'}) return n", Map()).awaitResult
      //Checking
      (parse(res) \\ "data").extractOpt[Any].toString mustEqual "Some(Map(data -> Map(b -> srt, a -> 123)))"
      logger.allCounts mustEqual (4,2,0,0,0)}
    "fail on execute Cypher with raw result, if statement incorrect" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      val provider = new RESTProvider(dbURL = url, auth = userPassword, logger)
      //Running
      val res = provider.executeCypherWithRawResult("tttttt (n:N{a:123,b:'srt'}) return n", Map()).awaitFailure
      //Checking
      res must haveClass[DatabaseHTTPException]
      logger.allCounts mustEqual (2,2,0,0,1)}
    "execute Cypher with raw result with parameters" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      val provider = new RESTProvider(dbURL = url, auth = userPassword, logger)
      //Running
      val res1 = provider.executeCypherWithRawResult(
        "create (n:N{a:{p1},b:'srt'}) return n", Map("p1" → 123456)).awaitResult
      val res2 = provider.executeCypherWithRawResult(
        "create (n:N{a:123,b:{p2}}) return n", Map("p2" → "string")).awaitResult
      val res3 = provider.executeCypherWithRawResult(
        "create (n:N{p3}) return n", Map("p3" → Map("a" → 321, "b" → "trs"))).awaitResult
      //Checking
      (parse(res1) \\ "data").extractOpt[Any].toString mustEqual "Some(Map(data -> Map(b -> srt, a -> 123456)))"
      (parse(res2) \\ "data").extractOpt[Any].toString mustEqual "Some(Map(data -> Map(b -> string, a -> 123)))"
      (parse(res3) \\ "data").extractOpt[Any].toString mustEqual "Some(Map(data -> Map(b -> trs, a -> 321)))"
      logger.allCounts mustEqual (10,2,0,0,0)}
    "execute Cypher with parsed result" in {
      //Preparing
      val logger = new DummyLogger("RESTProviderTest")
      val provider = new RESTProvider(dbURL = url, auth = userPassword, logger)
      //Running
      val res = provider.executeCypherWithJSONResult("create (n:N{a:123,b:'srt'}) return n", Map()).awaitResult
      //Checking
      (res \\ "data").extractOpt[Any].toString mustEqual "Some(Map(data -> Map(b -> srt, a -> 123)))"
      logger.allCounts mustEqual (4,2,0,0,0)}}}