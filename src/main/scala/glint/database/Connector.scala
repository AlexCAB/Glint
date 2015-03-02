package glint.database
import org.neo4j.jdbc.Neo4jConnection

/**
 * Connector provider interface
 * Created by CAB on 02.03.2015.
 */

trait Connector {
  /**
   * Return an connection to DB
   */
  def getConnection:Neo4jConnection}
