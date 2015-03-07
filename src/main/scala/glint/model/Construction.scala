package glint.model
import glint.serialization.{RawRelation, RawNode}

/**
 * Model node and relation constructor|deconstructor
 * @param model - an data model
 * Created by CAB on 07.03.2015.
 */

class Construction(model:Model) {
  //Override methods
  override def toString:String = s"glint.model.Construction(model = ${model.getClass.getCanonicalName})"
  //Methods
  /**
   * Reconstruction of node
   * @param rawNode - raw node data
   * @tparam T - node type (from defined in the model)
   * @return - Node instance or throw ConstructionException
   */
  def reconstructNode[T <: Node](rawNode:RawNode):T = {

    ???

  }
  /**
   * Reconstruction of relation
   * @param rawRelation - raw relation data
   * @tparam T - relation type (from defined in the model)
   * @return - Relation instance or throw ConstructionException
   */
  def reconstructRelation[T <: Relation](rawRelation:RawRelation):T = {

    ???

  }
  /**
   * Reconstruction of node if exist in model
   * @param rawNode - raw node data
   * @return - Node instance or throw ConstructionException
   */
  def reconstructAnyNode(rawNode:RawNode):Node = {

    ???

  }
  /**
   * Reconstruction of relation if exist in model
   * @param rawRelation - raw relation data
   * @return - Relation instance or throw ConstructionException
   */
  def reconstructAnyRelation(rawRelation:RawRelation):Relation = {

    ???

  }

}
