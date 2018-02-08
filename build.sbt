name := "sample-scala"

version := "0.1"

scalaVersion := "2.12.4"

lazy val root = (project in file("."))
  .settings(sharedSettings)
  .dependsOn(core, codegen, repository, service)

lazy val sharedSettings = Seq(
  scalacOptions := Seq("-feature", "-unchecked", "-deprecation"),
  libraryDependencies ++= Seq(
    "mysql" % "mysql-connector-java" % "5.1.45",
    "com.typesafe.slick" %% "slick" % "3.2.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4"
  )
)

lazy val core = project
  .settings(sharedSettings)

lazy val repository = project
  .settings(sharedSettings)
  .dependsOn(codegen)

lazy val service = project
  .settings(sharedSettings)
  .dependsOn(repository)

lazy val codegen = project
  .settings(sharedSettings)
  .settings(libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.2.0")
  .settings(slick := slickCodeGenTask.value)

lazy val slick = taskKey[Seq[File]]("generate tables task")
lazy val slickCodeGenTask = Def.task {
  val dir = new File(baseDirectory.value.getPath.replace("/codegen", ""))
  val cp = (fullClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  import com.typesafe.config.ConfigFactory
  val config = ConfigFactory.parseFile(new File((dir / "src/main/resources/application.conf").getPath))
  val jdbcDriver = config.getString("mysql.driver")
  val url = config.getString("mysql.url")
  val user = config.getString("mysql.user")
  val password = config.getString("mysql.password")
  val pkg = config.getString("codegen.pkg")

  val outputDir = (dir / "repository/src/main/scala").getPath

  r.run("codegen.CustomizedCodeGenerator", cp.files, Array(jdbcDriver, url, user, password, outputDir, pkg), s.log).failed foreach (sys error _.getMessage)
  val fname = outputDir + pkg.replace(".","/") + "/Tables.scala"
  Seq(file(fname))
}



