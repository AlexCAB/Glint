name := "Glint"

version := "0.0.0"

resolvers ++= Seq(
  "Sonatype Releases" at "http://m2.neo4j.org/content/groups/public/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")

lazy val macro = (project in file("macro")) settings( 
  scalaVersion := "2.11.5",
  libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)
)
  
lazy val root = project.in(file(".")).dependsOn(macro) settings(
  scalaVersion := "2.11.5",
  libraryDependencies ++= Seq(
//    "org.neo4j" % "neo4j-jdbc" % "2.1.4",
//    "org.neo4j" % "neo4j-kernel" % "2.1.7",
//    "org.neo4j" % "neo4j-lucene-index" % "2.1.7",
//    "org.neo4j" % "neo4j-cypher" % "2.1.7",
    "com.ning" % "async-http-client" % "1.8.10",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
    "org.slf4j" % "slf4j-api" % "1.7.10",
    "org.slf4j" % "slf4j-simple" % "1.7.10",
    "com.sun.jersey" % "jersey-core" % "1.19",
    "org.json4s" %% "json4s-core" % "3.2.11",
    "org.json4s" %% "json4s-native" % "3.2.11",
    "org.specs2" %% "specs2-core" % "3.0" % "test"
  )
)
 