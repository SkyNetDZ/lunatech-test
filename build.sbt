name := "lunatech-test"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.11.11"

libraryDependencies += guice
libraryDependencies += jdbc

libraryDependencies ++= Seq( javaJdbc , javaWs )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

libraryDependencies ++= Seq("org.mongodb.morphia" % "morphia" % "1.3.2", "org.mongodb" % "mongo-java-driver" % "3.2.2")

      