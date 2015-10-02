name := "imagine"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache
)     

play.Project.playJavaSettings

libraryDependencies += "com.cloudinary" % "cloudinary-http42" % "1.2.1"

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "4.1.3.Final"

requireJs += "main.js"