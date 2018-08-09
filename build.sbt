name := """play-with-mapstruct"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
//gulpForce := false

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  ehcache,
  ws,
  filters,
  evolutions,
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.2.6",
  "com.typesafe.play" %% "play-mailer" % "6.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
  "de.svenkubiak" % "jBCrypt" % "0.4",
  "org.mapstruct" % "mapstruct-jdk8" % "1.2.0.Final",
  "org.mapstruct" % "mapstruct-processor" % "1.2.0.Final",
  "commons-io" % "commons-io" % "2.6",
)