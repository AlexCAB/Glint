package glint.database
import com.ning.http.client.Response
import dispatch.Req

/**
 * Database exceptions
 * Created by CAB on 03.03.2015.
 */

/**
 * General exception
 */
class DatabaseException(msg:String) extends Exception(msg)

/**
 * HTTP exception
 */
class DatabaseHTTPException(msg:String, code:Int, request:Req, response:Option[Response]) extends DatabaseException(msg)