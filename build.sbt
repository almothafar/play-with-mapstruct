name := """play-with-mapstruct"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, Yeoman, LauncherJarPlugin)

// The Typesafe snapshots repository
resolvers += Resolver.url("Typesafe Ivy Snapshots Repository", url("https://oss.sonatype.org/content/repositories/snapshots/"))(Resolver.ivyStylePatterns)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  filters,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.39",
  "com.typesafe.play" %% "play-mailer" % "5.0.0",
  "de.svenkubiak" % "jBCrypt" % "0.4",
  "org.mapstruct" % "mapstruct-jdk8" % "1.1.0.CR1",
  "org.mapstruct" % "mapstruct-processor" % "1.1.0.CR1",
  "commons-io" % "commons-io" % "2.5"
)