package glint.cypher
import glint.model.Model


/**
 * Trite to execution raw Cypher statements.
 * Should be mix only with an Model instance.
 * Created by CAB on 02.03.2015.
 */

trait Cypher {
  //Get model
  private val model = this match{
    case m:Model ⇒ m
    case _ ⇒ throw new UnsupportedOperationException("Use glint.cypher.Cypher trite only with glint.model.Model")}
  //Methods
  /**
   * Create an Query instance to handling given query string
   * @param q - query string
   * @tparam T - result type
   * @return - Query instance
   */
  def query[T](q:String):Query[T] = new Query[T](q, model.connector)


}
