package glint.serialization
import glint.model.Construction
import org.json4s.JsonAST.JValue


/**
 * Implementation of base serialization
 * @param construction - model data constructor|deconstructor
 * Created by CAB on 07.03.2015.
 */

class BaseSerialization(construction:Construction) extends Serialization{
  //Override methods
  override def toString:String = s"glint.serialization.BaseSerialization(construction = $construction)"
  /**
   * Try to recognize data type by given JSON and deserialize it.
   * @param json - Neo4j response
   * @return - In base implementation: List of base type (String, Int, Ling, Boolean, List[T]), node,
   *         relations. Or N tuple of they.
   */
  def deserializeAny(json:JValue):List[Any] = {

    ???

  }
  /**
   * Deserialize Given type, throw ExceptionSerialization if not possible
   * @param json - Neo4j response
   * @tparam T - In base implementation: List of base type (String, Int, Ling, Boolean, List[T]), node,
   *           relations. Or N tuple of they.
   * @return - instance of T
   */
  def deserialize[T](json:JValue):List[T] = {

    ???

  }

}
