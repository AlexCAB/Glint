package glint.cypher
import glint.database.RESTProvider
import glint.model.{Model,Node}
import org.scalatest.{ WordSpecLike, Matchers, ParallelTestExecution }
import org.scalatest.concurrent.{ ScalaFutures, AsyncAssertions, PatienceConfiguration }
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Success,Failure}


/**
 * Running of simple Cypher statement.
 * Created by CAB on 02.03.2015.
 */

class RunStatement extends WordSpecLike with Matchers with ScalaFutures with ParallelTestExecution with AsyncAssertions{
  //Data model
  object TestModel extends Model(new RESTProvider(auth = Some("neo4j","1234"))) with Cypher{
    //Nodes
    class A(i:Int,s:String) extends Node}
  //Testing
  "creating of node" in {
    import TestModel._
    System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO")
    //Cypher
    val futureResult = query[A]("match (a:A) return a").toRaw
    futureResult.onComplete {
      case Success(s) ⇒ println("Result: " + s)
      case Failure(e) ⇒ println("Error: " + e)}
    Await.ready(futureResult, Duration(10, "second"))

//    val futureResult = query[A]("create (a:B) return a").toList





  }



}
