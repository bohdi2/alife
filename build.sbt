name := "alife"

version := "1.0"

scalaVersion := "2.10.2"

fork in run := true

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.1",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.1" % "test",
  "org.scala-lang" % "scala-library" % "2.10.2",
  "org.scala-lang" % "scala-swing" % "2.10.2",
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test")

atmosSettings

