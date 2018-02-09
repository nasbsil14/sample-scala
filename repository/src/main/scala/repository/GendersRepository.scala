package repository

import db.test.Tables._
import db.test.Tables.profile.api._

import scala.concurrent.Future

trait GendersRepository {
  private val db = Database.forConfig("mysql")
  private def gendersTableAutoInc = Genders returning Genders.map(_.gendercode)

  def create(gender: GendersRow): Future[Byte] = db.run { gendersTableAutoInc += gender }

  def update(gender: GendersRow): Future[Int] = db.run { Genders.filter(_.gendercode === gender.gendercode).update(gender) }

  def getByCode(code: Byte): Future[Option[GendersRow]] = db.run { Genders.filter(_.gendercode === code).result.headOption }

  def getAll(): Future[List[GendersRow]] = db.run { Genders.to[List].result }

  def delete(code: Byte): Future[Int] = db.run { Genders.filter(_.gendercode === code).delete }

}
object GendersRepository extends GendersRepository


