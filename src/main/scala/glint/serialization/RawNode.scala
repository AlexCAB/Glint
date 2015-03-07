package glint.serialization


/**
 * Raw node data structure
 * Created by CAB on 07.03.2015.
 */

case class RawNode(
  id:Long,
  labels:List[String],
  data:Map[String,Any])
