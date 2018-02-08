import slick.jdbc.MySQLProfile

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global

import db.test.Tables._
import db.test.Tables.profile.api._

object Main extends App {
  val db = MySQLProfile.api.Database.forConfig("mysql")

  val q = Users.to[List]

  Await.result(db.run(q.result).map { result =>
    println(result.mkString("\n"))
  }, 10 seconds)

}
