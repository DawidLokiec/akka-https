organization := "com.github.dawidlokiec"
name := "akka-https"
val $version = "0.2.0"
version := $version
assemblyJarName in assembly := s"akka-https-${$version}.jar"
scalaVersion := "2.13.4"
val akkaVersion = "2.6.11"
val akkaHttpVersion = "10.2.3"
val slf4jVersion = "1.7.30"
val akkaHttpCors = "1.1.1"

//noinspection SpellCheckingInspection
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion % "provided",
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion % "provided",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion % "provided",

  "ch.megard" %% "akka-http-cors" % akkaHttpCors,

  "org.slf4j" % "slf4j-simple" % slf4jVersion % "provided",
)

val scalaTest = "3.2.3"
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % scalaTest % Test,
)