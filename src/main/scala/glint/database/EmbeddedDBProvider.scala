package glint.database
//import java.util.Properties
//import org.neo4j.graphdb.factory.{GraphDatabaseSettings, GraphDatabaseFactory}
//import org.neo4j.jdbc.Neo4jConnection
//import org.neo4j.jdbc.Driver


/**
 * Embedded database provider implementation
 * Created by CAB on 02.03.2015.
 */

//todo rewrite to new interface
//todo look like embedded Neo4j not support Scala 2.11
//class EmbeddedDBProvider(dbPath:String) extends Provider{
//  //Get path
//  private val path = dbPath match{
//    case "" ⇒ getClass.getClassLoader.getResource("").getPath + "default_embedded_db"
//    case _  ⇒ dbPath}
//  //Create DB
//  private val graphDb = new GraphDatabaseFactory()
//    .newEmbeddedDatabaseBuilder(path)
//    .setConfig( GraphDatabaseSettings.allow_store_upgrade, "true" )
//    .newGraphDatabase
//  Runtime.getRuntime.addShutdownHook(new Thread{override def run() = graphDb.shutdown()})
//  //Install Driver
//  private val props = new Properties
//  props.put( "db", graphDb)
//  private val driver = new Driver
//  private val connection = driver.connect("jdbc:neo4j:instance:db", props)
//  //Override methods
//  override def toString:String = s"glint.database.EmbeddedDBProvider(path = $path)"
//  //Methods
//  def getConnection:Neo4jConnection = connection}
