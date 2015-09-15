name := "imagine"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

play.Project.playJavaSettings

libraryDependencies += "com.cloudinary" % "cloudinary-http42" % "1.2.1"

requireJs += "main.js"