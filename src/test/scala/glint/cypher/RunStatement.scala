package glint.cypher
import glint.database.RESTProvider
import glint.model.{Model,Node}
import org.specs2.mutable.Specification
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Success,Failure}


/**
 * Running of simple Cypher statement.
 * Created by CAB on 02.03.2015.
 */

class RunStatement extends Specification{
  //Parameters
  val userPassword = Some("neo4j","1234")
  //Preparing
  System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO")
  //Data model
  object TestModel extends Model(new RESTProvider(auth = userPassword)) with Cypher{
    //Nodes
    class A(i:Int,s:String) extends Node}
  //Testing
  "RunStatement" should {
    "creating of node" in {
      import TestModel._

      //Cypher
      val futureResult = query("crefate (a:A{ a:123, b:{par1}}) return a").withParams(Map("par1" → "test")).toRaw
      //    val futureResult = query[A]("create (a:A{ a:123456, b:{par1}}) return a").withParam("par1" → "test").toRaw
      //    val futureResult = query[A]("create (a:A{ a:{par2}, b:{par1}}) return a").withParam("par1" → "test").withParam("par2" → 4321).toRaw

      //    val futureResult = query[A]("create (a:A{par1}) return a").withParams(Map("par1" → Map("a" →123, "b" → "ddd"))).toRaw


      futureResult.onComplete {
        case Success(s) ⇒ println("Result: " + s)
        case Failure(e) ⇒ println("Error: " + e)
      }
      Await.ready(futureResult, Duration(10, "second"))

     todo

    }

  }

}
