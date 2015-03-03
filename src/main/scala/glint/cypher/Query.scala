package glint.cypher
import glint.database.Provider
import scala.concurrent.Future
import java.util.Collections


/**
 * Represent Cypher query
 * Created by CAB on 02.03.2015.
 */

class Query[T](query:String, provider:Provider) {
  //Override methods
  override def toString:String = s"glint.cypher.Query(query = $query, connector = $provider)"
  //Methods
  /**
   * Convert Query to raw JSON response
   */
  def toRaw:Future[String] = provider.executeCypherWithRawResult(query)
  /**
   * Convert Query to future list of result
   */
  def toList:Future[List[T]] = ???






}
