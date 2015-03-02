name := "Glint"

version := "0.0.0"

resolvers ++= Seq(
  "Sonatype Releases" at "http://m2.neo4j.org/content/groups/public/")

lazy val macro = (project in file("macro")) settings( 
  scalaVersion := "2.11.5",
  libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)
)
  
lazy val root = project.in(file(".")).dependsOn(macro) settings(
  scalaVersion := "2.11.5",
  libraryDependencies ++= Seq(
    "org.neo4j" % "neo4j-jdbc" % "2.1.4",
    "org.neo4j" % "neo4j-kernel" % "2.1.7",
    "org.neo4j" % "neo4j-lucene-index" % "2.1.7",
    "org.neo4j" % "neo4j-cypher" % "2.1.7",
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"    
  )
)
 