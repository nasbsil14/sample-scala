package repository

import db.test.Tables._
import db.test.Tables.profile.api._

import scala.concurrent.Future

trait JobsRepository {
  private val db = Database.forConfig("mysql")
  private def jobsTableAutoInc = Jobs returning Jobs.map(_.jobscode)

  def create(job: JobsRow): Future[Byte] = db.run { jobsTableAutoInc += job }

  def update(job: JobsRow): Future[Int] = db.run { Jobs.filter(_.jobscode === job.jobscode).update(job) }

  def getByCode(code: Byte): Future[Option[JobsRow]] = db.run { Jobs.filter(_.jobscode === code).result.headOption }

  def getAll(): Future[List[JobsRow]] = db.run { Jobs.to[List].result }

  def delete(code: Byte): Future[Int] = db.run { Jobs.filter(_.jobscode === code).delete }

}
object JobsRepository extends JobsRepository

