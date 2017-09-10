name := "AkkaKamonTest"

version := "1.0"

scalaVersion := "2.12.3"

val kamonVersion = "0.6.6"

libraryDependencies ++= Seq(
   "com.typesafe.akka" %% "akka-actor" % "2.5.4",
   "com.typesafe.akka" %% "akka-slf4j" % "2.5.4",
   "org.slf4j" % "slf4j-simple" % "1.7.25",
   "io.kamon" %% "kamon-core" % kamonVersion,
   "io.kamon" %% "kamon-jmx" % kamonVersion,
   "io.kamon" %% "kamon-akka-2.4" % kamonVersion
)