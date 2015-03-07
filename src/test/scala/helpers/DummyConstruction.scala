package helpers
import glint.model.{Construction, Node, Relation}
import glint.serialization.{RawRelation, RawNode}


/**
 * Model node and relation constructor|deconstructor for test
 * Created by CAB on 07.03.2015.
 */

class DummyConstruction extends Construction{

  //Methods

  override def reconstructNode[T <: Node](rawNode:RawNode):T = {

    ???

  }

  override def reconstructRelation[T <: Relation](rawRelation:RawRelation):T = {

    ???

  }

  override def reconstructAnyNode(rawNode:RawNode):Node = {

    ???

  }

  override def reconstructAnyRelation(rawRelation:RawRelation):Relation = {

    ???

  }

}

