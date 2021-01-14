organization := "com.github.dawidlokiec"
name := "akka-https"
val $version = "0.1.0"
version := $version
assemblyJarName in assembly := s"akka-https-${$version}.jar"
scalaVersion := "2.13.4"
val akkaVersion = "2.6.10"
val akkaHttpVersion = "10.2.2"
val slf4jVersion = "1.7.30"

//noinspection SpellCheckingInspection
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion % "provided",
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion % "provided",
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion % "provided",
  "org.slf4j" % "slf4j-simple" % slf4jVersion % "provided",
)