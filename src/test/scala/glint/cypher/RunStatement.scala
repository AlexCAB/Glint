package glint.cypher
import glint.database.JDBCConnector
import glint.model.{Model,Node}
import org.scalatest._


/**
 * Running of simple Cypher statement.
 * Created by CAB on 02.03.2015.
 */

class RunStatement extends WordSpecLike with Matchers {
  //Data model
  object TestModel extends Model with Cypher{
    //Nodes
    class A(i:Int,s:String) extends Node}
  //Testing
  "creating of node" in {
    import TestModel._
    //Cypher
    val futureResult = query[A]("match (a:A) return a").toList
//    val futureResult = query[A]("create (a:B) return a").toList





  }



}
