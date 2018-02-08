package codegen

import slick.jdbc.MySQLProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object CustomizedCodeGenerator {
  def main(args: Array[String]): Unit = {
    println("codegen START")

    val jdbcDriver = args(0)
    val url = args(1)
    val user = args(2)
    val password = args(3)
    val output = args(4)
    val pkg = args(5)

    val driver = MySQLProfile
    val db = driver.api.Database.forURL(url, user, password, driver=jdbcDriver)
    // filter out desired tables
    val included = Seq("Users")
    val codegen = db.run{
      driver.defaultTables.flatMap( driver.createModelBuilder(_,false).buildModel )
      // driver.defaultTables.map(_.filter(t => included contains t.name.name)).flatMap( driver.createModelBuilder(_,false).buildModel )
    }.map { model =>
      new slick.codegen.SourceCodeGenerator(model) {
        model.tables.foreach(table => println(table.name.table))
      }
    }

    Await.ready(
      codegen.map(generator => {
        generator.writeToFile(
          "slick.jdbc.MySQLProfile",
          output,
          pkg,
          "Tables", // container objects name
          "Tables.scala"
        )
      }),
      10.seconds
    )

    println("END")
  }
}