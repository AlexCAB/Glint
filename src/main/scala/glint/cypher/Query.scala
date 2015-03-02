package glint.cypher
import glint.database.Connector
import scala.concurrent.Future
import java.util.Collections


/**
 * Represent Cypher query
 * Created by CAB on 02.03.2015.
 */

class Query[T](query:String, connector:Connector) {
  //Override methods
  override def toString:String = s"glint.cypher.Query(query = $query, connector = $connector)"
  //Methods
  /**
   * Convert Query to future list of result
   */
  def toList:Future[List[T]] = {


    val r = connector.getConnection.executeQuery(query, Collections.emptyMap())

    println(r)





    null

  }






}
