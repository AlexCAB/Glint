package glint.database
import helpers.DummyLogger
import org.specs2.mutable.Specification
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Success,Failure}


/**
 * Test for RESTProvider classclean
 * Created by CAB on 04.03.2015.
 */

class RESTProviderTest extends Specification{
  //Parameters
  val userPassword = Some("neo4j","1234")
  //Preparing
  System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO")
  //Testing
  "RESTProvider" should {
    "connect to DB" in {
      val logger = new DummyLogger("RESTProviderTest")
      val provider = new RESTProvider(dbURL = "localhost:7474", auth = userPassword, logger)
      //
      todo

    }
    "not connect to DB with wrong URL, user name or password" in {



      todo
    }
    "execute Cypher with raw result" in {


      todo
    }
    "fail on execute Cypher with raw result, if statement incorrect" in {
      todo
    }

  }

//
//    import TestModel._
//
//    //Cypher
//    val futureResult = query("crefate (a:A{ a:123, b:{par1}}) return a").withParams(Map("par1" → "test")).toRaw
//    //    val futureResult = query[A]("create (a:A{ a:123456, b:{par1}}) return a").withParam("par1" → "test").toRaw
//    //    val futureResult = query[A]("create (a:A{ a:{par2}, b:{par1}}) return a").withParam("par1" → "test").withParam("par2" → 4321).toRaw
//
//    //    val futureResult = query[A]("create (a:A{par1}) return a").withParams(Map("par1" → Map("a" →123, "b" → "ddd"))).toRaw
//
//
//    futureResult.onComplete {
//      case Success(s) ⇒ println("Result: " + s)
//      case Failure(e) ⇒ println("Error: " + e)}
//    Await.ready(futureResult, Duration(10, "second"))








}
