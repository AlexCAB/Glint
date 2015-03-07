package glint.serialization


/**
 * Raw relation data structure
 * Created by CAB on 07.03.2015.
 */

case class RawRelation(
  id:Long,
  typeName:String,
  data:Map[String,Any])
