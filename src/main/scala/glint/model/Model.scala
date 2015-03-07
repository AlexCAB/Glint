package glint.model
import glint.database.Provider
import glint.serialization.{BaseSerialization, Serialization}
import scala.concurrent.ExecutionContext


/**
 * Trait used for definition of data model container
 * Created by CAB on 01.03.2015.
 */

abstract class Model(implicit val executor: ExecutionContext){
  //Fields
  val provider:Provider
  val serialization:Serialization = new BaseSerialization(new Construction(this))
  //Override methods
  override def toString:String =
    s"${getClass.getCanonicalName}(provider = $provider, serialization = $serialization)"}
