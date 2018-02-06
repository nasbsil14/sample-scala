package codegen

import slick.model.Model
import scala.concurrent._
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

import slick.jdbc.MySQLProfile

object CustomizedCodeGenerator {
  def main(args: Array[String]): Unit = {
    Await.ready(
      codegen.map(_.writeToFile(
        "com.mysql.jdbc.Driver",
        args(0),
        "codegen",
        "Tables",
        "Tables.scala"
      )),
      20.seconds
    )
  }

  val driver = MySQLProfile
  val db = driver.api.Database.forConfig("mysql")
  // filter out desired tables
  val included = Seq("Users")
  val codegen = db.run{
    driver.defaultTables.map(_.filter(t => included contains t.name.name)).flatMap( driver.createModelBuilder(_,false).buildModel )
  }.map { model =>
    new slick.codegen.SourceCodeGenerator(model) {
    }
  }
}