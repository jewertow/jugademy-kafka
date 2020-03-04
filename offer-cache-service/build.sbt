scalaVersion     := "2.12.1"
version          := "0.1.0-SNAPSHOT"
organization     := "pl.jugademy"
name             := "offer-cache-service"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case PathList("reference.conf") => MergeStrategy.concat
  case _ => MergeStrategy.first
}

libraryDependencies ++= {
  val akkaVersion = "2.5.26"
  val akkaHttpVersion = "10.1.11"
  Seq(
    "com.typesafe.akka"   %% "akka-slf4j"                         % akkaVersion,
    "com.typesafe.akka"   %% "akka-stream"                        % akkaVersion,
    "com.typesafe.akka"   %% "akka-http"                          % akkaHttpVersion,
    "com.typesafe.akka"   %% "akka-http-spray-json"               % akkaHttpVersion,

    "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
    "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",

    // json
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.12" % "2.8.8",
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.8.8",

    // logging
    "ch.qos.logback"      %  "logback-classic"              % "1.1.3",

    // test
    "com.typesafe.akka"   %% "akka-testkit" % akkaVersion   % "test",
    "org.scalatest"       %% "scalatest"    % "3.0.0"       % "test"
  )
}

