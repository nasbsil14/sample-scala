package db.connection

import slick.jdbc.MySQLProfile

trait MySQLAdapter extends DbAdapter {
  val driver = MySQLProfile
  import driver.api._
  val db: Database = MySqlDB.connectionPool
}
private[connection] object MySqlDB {
  import slick.jdbc.MySQLProfile.api._
  val connectionPool = Database.forConfig("mysql")
}