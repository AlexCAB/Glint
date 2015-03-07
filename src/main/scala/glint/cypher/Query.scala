package glint.cypher
import glint.database.Provider
import glint.model.Model
import glint.serialization.Serialization
import scala.concurrent.{ExecutionContext, Future}
import reflect.runtime.universe._


/**
 * Represent Cypher query
 * Created by CAB on 02.03.2015.
 */

class Query[T : TypeTag](model:Model, query:String, params:Map[String,Any] = Map()){
  implicit val executor = model.executor
  //Override methods
  override def toString:String = s"glint.cypher.Query(query = $query, model = $model)"
  //Methods
  /**
   * Add Cypher parameters to the query
   *  @param newParams - Cypher parameters: Map(< name > → < value >)
   */
  def withParams(newParams:Map[String,Any]):Query[T] =
    new Query(model, query, params ++ newParams)
  /**
   * Add one Cypher parameter to the query
   *  @param newParam - Cypher parameters: Map(< name > → < value >)
   */
  def withParam(newParam:Tuple2[String,Any]):Query[T] =
    new Query(model, query, params + newParam)
  /**
   * Convert Query to raw JSON response
   */
  def toRaw:Future[String] = model.provider.executeCypherWithRawResult(query, params)
  /**
   * Convert Query to future list of result
   */
  def toList:Future[List[T]] = {
    //Make DB request
    model.provider.executeCypherWithJSONResult(query, params)
    //Deserialization
    .map(json ⇒ typeOf[T] match{
      case t if t =:= typeOf[Any] ⇒ model.serialization.deserializeAny(json).map(_.asInstanceOf[T])
      case _ ⇒ model.serialization.deserialize[T](json)})}






}
