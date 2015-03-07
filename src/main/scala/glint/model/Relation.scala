package glint.model


/**
 * Base trait for relationships.
 * Created by CAB on 01.03.2015.
 */

trait Relation extends Glint {
  val source:Node = null
  val target:Node = null}
