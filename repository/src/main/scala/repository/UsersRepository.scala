package repository

import db.test.Tables._
import db.test.Tables.profile.api._

import scala.concurrent.Future

trait UsersRepository {
  private val db = Database.forConfig("mysql")
  private def usersTableAutoInc = Users returning Users.map(_.userid)

  /**
    * @param user
    * create new user
    */
  def create(user: UsersRow): Future[Int] = db.run { usersTableAutoInc += user }

  /**
    * @param user
    * update existing user
    */
  def update(user: UsersRow): Future[Int] = db.run { Users.filter(_.userid === user.userid).update(user) }

  /**
    * @param id
    * Get user by id
    */
  def getById(id: Int): Future[Option[UsersRow]] = db.run { Users.filter(_.userid === id).result.headOption }

  /**
    * @return
    * Get all users
    */
  def getAll(): Future[List[UsersRow]] = db.run { Users.to[List].result }

  /**
    * @param id
    * delete user by id
    */
  def delete(id: Int): Future[Int] = db.run { Users.filter(_.userid === id).delete }

}

object UsersRepository extends UsersRepository
