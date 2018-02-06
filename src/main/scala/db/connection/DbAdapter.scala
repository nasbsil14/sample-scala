package db.connection

import slick.jdbc.JdbcProfile

trait DbAdapter {
  val driver: JdbcProfile
  import driver.api._
  val db: Database
}
