name := "sample-scala"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.36",
//  "mysql" % "mysql-connector-java" % "6.0.6",
  "com.typesafe.slick" %% "slick" % "3.2.0",
//  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)