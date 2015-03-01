package playing
import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context


/**
 * An plying with Scala macro
 * Created by CAB on 01.03.2015.
 */

object Macro {
  def print(msg: String): Unit = macro impl
  def impl(c: Context)(msg: c.Expr[String]): c.Expr[Unit] = {
    import c.universe._
    reify{
      println("Macro: " + msg.splice)}}}