name := "RetailSalesApp"

version := "0.1"

scalaVersion := "2.12.10"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

val scalazVersion = "7.3.2"


lazy val root = (project in file("."))

/*
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.7.0",

)
*/


//libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  //  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  //"org.scalaz" %% "scalaz-typelevel" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test"
)

