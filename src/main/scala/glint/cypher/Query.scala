package glint.cypher
import glint.database.Provider
import scala.concurrent.Future
import java.util.Collections


/**
 * Represent Cypher query
 * Created by CAB on 02.03.2015.
 */

class Query[T](query:String, provider:Provider, params:Map[String,Any] = Map()) {
  //Override methods
  override def toString:String = s"glint.cypher.Query(query = $query, connector = $provider)"
  //Methods
  /**
   * Add Cypher parameters to the query
   *  @param newParams - Cypher parameters: Map(< name > → < value >)
   */
  def withParams(newParams:Map[String,Any]):Query[T] = new Query(query, provider, params ++ newParams)
  /**
   * Add one Cypher parameter to the query
   *  @param newParam - Cypher parameters: Map(< name > → < value >)
   */
  def withParam(newParam:Tuple2[String,Any]):Query[T] = new Query(query, provider, params + newParam)
  /**
   * Convert Query to raw JSON response
   */
  def toRaw:Future[String] = provider.executeCypherWithRawResult(query, params)
  /**
   * Convert Query to future list of result
   */
  def toList:Future[List[T]] = ???






}
