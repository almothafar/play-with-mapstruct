// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.8")

// Play enhancer - this automatically generates getters/setters for public fields
// and rewrites accessors of these fields to use the getters/setters. Remove this
// plugin if you prefer not to have this feature, or disable on a per project
// basis using disablePlugins(PlayEnhancer) in your build.sbt
addSbtPlugin("com.typesafe.sbt" % "sbt-play-enhancer" % "1.1.0")

// Play Ebean support, to enable, uncomment this line, and enable in your build.sbt using
// enablePlugins(PlayEbean). Note, uncommenting this line will automatically bring in
// Play enhancer, regardless of whether the line above is commented out or not.
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "3.0.2")


addSbtPlugin("com.tuplejump" % "sbt-yeoman" % "0.9.0")

// Not necessary but useful for development
// https://github.com/jamesward/play-auto-refresh
//addSbtPlugin("com.jamesward" % "play-auto-refresh" % "0.0.15")