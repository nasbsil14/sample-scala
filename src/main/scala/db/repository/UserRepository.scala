package db.repository

import java.sql.Timestamp

import db.connection.{DbAdapter, MySQLAdapter}
import db.model.Users
import slick.sql.SqlProfile.ColumnOption.SqlType

import scala.concurrent.Future

trait UsersRepository extends UsersTable { this: DbAdapter =>
  import driver.api._

  /**
    * @param users
    * create new user
    */
  def create(users: Users): Future[Int] = db.run { usersTableAutoInc += users }

  /**
    * @param users
    * update existing user
    */
  def update(users: Users): Future[Int] = db.run { usersTableQuery.filter(_.userId === users.userId.get).update(users) }

  /**
    * @param id
    * Get user by id
    */
  def getById(id: Int): Future[Option[Users]] = db.run { usersTableQuery.filter(_.userId === id).result.headOption }

  /**
    * @return
    * Get all users
    */
  def getAll(): Future[List[Users]] = db.run { usersTableQuery.to[List].result }

  /**
    * @param id
    * delete user by id
    */
  def delete(id: Int): Future[Int] = db.run { usersTableQuery.filter(_.userId === id).delete }
}
object UsersRepository extends UsersRepository with MySQLAdapter

private[repository] trait UsersTable { this: DbAdapter =>

  import driver.api._
  private[UsersTable] class UsersTable(tag: Tag) extends Table[Users](tag, "Users") {
    val userId = column[Int]("UserId", O.PrimaryKey, O.AutoInc)
    val name = column[String]("Name")

    val gender = column[Int]("Gender")
    val birthDay = column[Timestamp]("BirthDay")
    val job = column[Int]("Job")
    val areaCode = column[Int]("AreaCode")
    val status = column[Int]("Status")
    val registerDate = column[Timestamp]("RegisterDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
    val updateDate = column[Timestamp]("UpdateDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def * = (userId.?, name, gender, birthDay, job, areaCode, status, registerDate, updateDate) <> (Users.tupled, Users.unapply)

  }

  protected val usersTableQuery = TableQuery[UsersTable]
  protected def usersTableAutoInc = usersTableQuery returning usersTableQuery.map(_.userId)
}