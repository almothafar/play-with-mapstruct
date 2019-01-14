// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.7.0-RC9")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
//addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.2.2")

// Play Ebean support, to enable, uncomment this line, and enable in your build.sbt using
// enablePlugins(PlayEbean). Note, uncommenting this line will automatically bring in
// Play enhancer, regardless of whether the line above is commented out or not.

//addSbtPlugin("com.payintech" % "sbt-play-ebean" % "18.11")

addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "5.0.0-RC2")

//addSbtPlugin("com.github.mmizutani" % "sbt-play-gulp" % "0.2.0")

//dependencyOverrides ++= Seq(
//  "io.ebean" % "ebean" % "11.26.1",
//  "io.ebean" % "ebean-agent" % "11.26.1",
//  "io.ebean" % "ebean-migration" % "11.11.1"
//)
