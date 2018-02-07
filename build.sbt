name := "sample-scala"

version := "0.1"

scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(sharedSettings)
  .settings(slick := slickCodeGenTask.value) // register manual sbt command)
//  .settings(sourceGenerators in Compile += slickCodeGenTask.taskValue) // register automatic code generation on every compile, remove for only manual use)
  .dependsOn(codegen)


/** codegen project containing the customized code generator */
lazy val codegen = project
  .settings(sharedSettings)
  .settings(libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.2.0")


// shared sbt config between main project and codegen project
lazy val sharedSettings = Seq(
  scalacOptions := Seq("-feature", "-unchecked", "-deprecation"),
  libraryDependencies ++= Seq(
    "mysql" % "mysql-connector-java" % "6.0.6",
    "com.typesafe.slick" %% "slick" % "3.2.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4"
  )
)


// code generation task that calls the customized code generator
lazy val slick = taskKey[Seq[File]]("generate tables task")
lazy val slickCodeGenTask = Def.task {
  val dir = sourceManaged.value
  val cp = (dependencyClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  import com.typesafe.config.{ConfigFactory, Config}
  val config = ConfigFactory.parseFile(new File("src/main/resources/application.conf"))
  val jdbcDriver = config.getString("mysql.driver")
  val url = config.getString("mysql.url")
  val user = config.getString("mysql.user")
  val password = config.getString("mysql.password")
  val pkg = config.getString("codegen.pkg")
  val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder

  r.run("codegen.CustomizedCodeGenerator", cp.files, Array(jdbcDriver, url, user, password, outputDir, pkg), s.log).failed foreach (sys error _.getMessage)
  val fname = outputDir + "/db/Tables.scala"
  Seq(file(fname))
}



