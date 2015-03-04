package glint.database
import scala.concurrent.Future

/**
 * Provider interface
 * Created by CAB on 02.03.2015.
 */

trait Provider {
  /**
   * Execute Cypher and return raw JSON result
   * @param cypher - Cypher statement
   * @param params - Cypher parameters: Map(< name > → < value >)
   * @return - Future JSON string
   */
  def executeCypherWithRawResult(cypher:String, params:Map[String,Any]):Future[String]
}
