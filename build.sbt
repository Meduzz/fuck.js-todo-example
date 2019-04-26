enablePlugins(ScalaJSPlugin)

name := "fuck.js-todo-example"

version := "1.0.2"

scalaVersion := "2.12.6"

resolvers += "se.chimps.js" at "https://yamr.kodiak.se/maven"

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.1"

libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.6.5"

libraryDependencies += "se.chimps.js" %%% "fuckjs" % "Beta4"