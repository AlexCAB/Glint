package helpers
import org.slf4j.{Marker, Logger}
import scala.collection.mutable.{ListBuffer => MutList}


/**
 * Set of tools to help of testing of logging.
 * Created by CAB on 04.03.2015.
 */

class DummyLogger(name:String) extends Logger{
  //Variables
  private val traces = MutList[String]()
  private val debugs = MutList[String]()
  private val infos = MutList[String]()
  private val warns = MutList[String]()
  private val errors = MutList[String]()
  //Service methods
  def clear() = {
    traces.clear()
    debugs.clear()
    infos.clear()
    warns.clear()
    errors.clear()}
  def getAll:(List[String],List[String],List[String],List[String],List[String]) =
    (traces.toList, debugs.toList,infos.toList,warns.toList,errors.toList)
  def countOfTraces:Int = traces.size
  def countOfDebugs:Int = debugs.size
  def countOfInfos:Int = infos.size
  def countOfWarns:Int = warns.size
  def countOfErrors:Int = errors.size
  def allCounts:(Int,Int,Int,Int,Int) = (traces.size, debugs.size, infos.size, warns.size, errors.size)
  //Merhods
  def getName: String = ???
  def isTraceEnabled: Boolean = ???
  def trace(msg: String) = {
    println(s"[$name:DummyLogger; trace] $msg")
    traces += msg}
  def trace(format: String, arg: AnyRef) = ???
  def trace(format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def trace(format: String, arguments: AnyRef*) = ???
  def trace(msg: String, t: Throwable) = ???
  def isTraceEnabled(marker: Marker): Boolean = ???
  def trace(marker: Marker, msg: String) = ???
  def trace(marker: Marker, format: String, arg: AnyRef) = ???
  def trace(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def trace(marker: Marker, format: String, argArray: AnyRef*) = ???
  def trace(marker: Marker, msg: String, t: Throwable) = ???
  def isDebugEnabled: Boolean = ???
  def debug(msg: String) = {
    println(s"[$name:DummyLogger; debug] $msg")
    debugs += msg}
  def debug(format: String, arg: AnyRef) = ???
  def debug(format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def debug(format: String, arguments: AnyRef*) = ???
  def debug(msg: String, t: Throwable) = ???
  def isDebugEnabled(marker: Marker): Boolean = ???
  def debug(marker: Marker, msg: String) = ???
  def debug(marker: Marker, format: String, arg: AnyRef) = ???
  def debug(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def debug(marker: Marker, format: String, arguments: AnyRef*) = ???
  def debug(marker: Marker, msg: String, t: Throwable) = ???
  def isInfoEnabled: Boolean = ???
  def info(msg: String) = {
    println(s"[$name:DummyLogger; info] $msg")
    infos += msg}
  def info(format: String, arg: AnyRef) = ???
  def info(format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def info(format: String, arguments: AnyRef*) = ???
  def info(msg: String, t: Throwable) = ???
  def isInfoEnabled(marker: Marker): Boolean = ???
  def info(marker: Marker, msg: String) = ???
  def info(marker: Marker, format: String, arg: AnyRef) = ???
  def info(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def info(marker: Marker, format: String, arguments: AnyRef*) = ???
  def info(marker: Marker, msg: String, t: Throwable) = ???
  def isWarnEnabled: Boolean = ???
  def warn(msg: String) = {
    println(s"[$name:DummyLogger; warn] $msg")
    warns += msg}
  def warn(format: String, arg: AnyRef) = ???
  def warn(format: String, arguments: AnyRef*) = ???
  def warn(format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def warn(msg: String, t: Throwable) = ???
  def isWarnEnabled(marker: Marker): Boolean = ???
  def warn(marker: Marker, msg: String) = ???
  def warn(marker: Marker, format: String, arg: AnyRef) = ???
  def warn(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def warn(marker: Marker, format: String, arguments: AnyRef*) = ???
  def warn(marker: Marker, msg: String, t: Throwable) = ???
  def isErrorEnabled: Boolean = ???
  def error(msg: String) = {
    println(s"[$name:DummyLogger; error] $msg")
    errors += msg}
  def error(format: String, arg: AnyRef) = ???
  def error(format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def error(format: String, arguments: AnyRef*) = ???
  def error(msg: String, t: Throwable) = ???
  def isErrorEnabled(marker: Marker): Boolean = ???
  def error(marker: Marker, msg: String) = ???
  def error(marker: Marker, format: String, arg: AnyRef) = ???
  def error(marker: Marker, format: String, arg1: AnyRef, arg2: AnyRef) = ???
  def error(marker: Marker, format: String, arguments: AnyRef*) = ???
  def error(marker: Marker, msg: String, t: Throwable) = ???}




































