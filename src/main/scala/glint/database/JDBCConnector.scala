package glint.database
import java.util.Properties
import org.neo4j.jdbc.Driver
import org.neo4j.jdbc.Neo4jConnection


/**
 * Contains internal mechanics of DB with JDBC access
 * Created by CAB on 02.03.2015.
 */

class JDBCConnector(
  url:String = "jdbc:neo4j://localhost",
  port:Int = 7474,
  user:String = "",
  password:String = "")
extends Connector {
  //Construction
  private val props = new Properties
  if(user != ""){props.put("user", user)}
  if(password != ""){props.put("password", password)}
  props.setProperty("legacy", "true" )
  private val driver = new Driver
  private val connection = driver.connect( url + ":" + port, props)
  //Override methods
  override def toString:String = s"glint.database.JDBCConnector(url = $url, port = $port, user = $user)"
  //Methods
  def getConnection:Neo4jConnection = connection}












































