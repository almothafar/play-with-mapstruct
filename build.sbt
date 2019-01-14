name := """play-with-mapstruct"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
//gulpForce := false

scalaVersion := "2.12.8"

// You can easily navigate from error pages to IntelliJ directly into the source code,
// by using IntelliJ’s “remote file” REST API with the built in IntelliJ web server on port 63342.
javaOptions += "-Dplay.editor=http://localhost:63342/api/file/?file=%s&line=%s"

val javaVersion = settingKey[String]("The version of Java used for building.")

javaVersion := System.getProperty("java.version")

val java9AndSupLibraryDependencies: Seq[sbt.ModuleID] =
  if (!javaVersion.toString.startsWith("1.8")) {
    Seq(
      "com.sun.activation" % "javax.activation" % "1.2.0",
      "com.sun.xml.bind" % "jaxb-core" % "2.3.0",
      "com.sun.xml.bind" % "jaxb-impl" % "2.3.1",
      "javax.jws" % "javax.jws-api" % "1.1",
      "javax.xml.bind" % "jaxb-api" % "2.3.0",
      "javax.xml.ws" % "jaxws-api" % "2.3.1"
    )
  } else {
    Seq.empty
  }

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  ehcache,
  ws,
  filters,
  evolutions,
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.3.0",
  "com.typesafe.play" %% "play-mailer" % "6.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
  "de.svenkubiak" % "jBCrypt" % "0.4.1",
  "org.mapstruct" % "mapstruct-jdk8" % "1.2.0.Final",
  "org.mapstruct" % "mapstruct-processor" % "1.2.0.Final",
  "commons-io" % "commons-io" % "2.6",
) ++ java9AndSupLibraryDependencies

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.11.1" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.23.0" % Test
libraryDependencies += "org.hamcrest" % "java-hamcrest" % "2.0.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

PlayKeys.devSettings := Seq("play.server.http.idleTimeout" -> "180s")
