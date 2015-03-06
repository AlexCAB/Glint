package helpers
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Set of helpers for test of Futures
 * Created by CAB on 06.03.2015.
 */

trait FuturesHelpers {
  //Parameters
  private val timeout = 10.second
  //Helpers
  implicit class FutureToResult[T](ft:Future[T]){
    def awaitResult:T = Await.result(ft, timeout)
    def awaitFailure:Throwable = {
      try{
        val result = Await.result(ft, timeout)
        throw new Exception("[FuturesHelpers.awaitFailure] Get result: " + result)}
      catch{
        case t:Throwable ⇒ t}}}}
