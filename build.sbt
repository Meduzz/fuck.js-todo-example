enablePlugins(ScalaJSPlugin)

name := "fuck.js-todo-example"

version := "1.0.3"

scalaVersion := "2.12.6"

resolvers += "se.chimps.js" at "https://yamr.kodiak.se/maven"

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.7"

libraryDependencies += "se.chimps.js" %%% "fuckjs" % "Beta6.1"