name := "RetailSalesApp"

version := "0.1"

scalaVersion := "2.13.3"

scalaVersion := "2.12.1"

val scalazVersion = "7.3.2"
libraryDependencies += "org.scalaz" %% "scalaz-effect" % "7.3.2"
libraryDependencies += "org.scalaz" %% "scalaz-typelevel" % "7.1.17"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  //  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  //"org.scalaz" %% "scalaz-typelevel" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test"
)

