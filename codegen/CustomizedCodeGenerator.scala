package codegen

import slick.jdbc.MySQLProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object CustomizedCodeGenerator {
  def main(args: Array[String]): Unit = {

    val jdbcDriver = args(0)
    val url = args(1)
    val user = args(2)
    val password = args(3)

    val driver = MySQLProfile
    val db = driver.api.Database.forURL(url, user, password, driver=jdbcDriver)
    // filter out desired tables
    val included = Seq("Users")
    val codegen = db.run{
      driver.defaultTables.flatMap( driver.createModelBuilder(_,false).buildModel )
      // driver.defaultTables.map(_.filter(t => included contains t.name.name)).flatMap( driver.createModelBuilder(_,false).buildModel )
    }.map { model =>
      new slick.codegen.SourceCodeGenerator(model) {
        println(model)
      }
    }

    Await.ready(
      codegen.map(_.writeToFile(
        "slick.jdbc.MySQLProfile",
        args(4),
        args(5),
        "Tables",
        "Tables.scala"
      )),
      20.seconds
    )
  }
}