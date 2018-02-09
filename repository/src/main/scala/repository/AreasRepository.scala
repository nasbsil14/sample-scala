package repository

import db.test.Tables._
import db.test.Tables.profile.api._

import scala.concurrent.Future

trait AreasRepository {
  private val db = Database.forConfig("mysql")
  private def areasTableAutoInc = Areas returning Areas.map(_.areacode)

  def create(area: AreasRow): Future[Byte] = db.run { areasTableAutoInc += area }

  def update(area: AreasRow): Future[Int] = db.run { Areas.filter(_.areacode === area.areacode).update(area) }

  def getByCode(code: Byte): Future[Option[AreasRow]] = db.run { Areas.filter(_.areacode === code).result.headOption }

  def getAll(): Future[List[AreasRow]] = db.run { Areas.to[List].result }

  def delete(code: Byte): Future[Int] = db.run { Areas.filter(_.areacode === code).delete }

}
object AreasRepository extends AreasRepository

