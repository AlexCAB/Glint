name := "Glint"

version := "0.0.0"

resolvers ++= Seq()


lazy val macro = (project in file("macro")) settings( 
  scalaVersion := "2.11.5",
  libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _)
)
  
lazy val root = project.in(file(".")).dependsOn(macro) settings(
  scalaVersion := "2.11.5",
  libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"    
  )
)
 