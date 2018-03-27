package db.repository

import java.sql.Timestamp

import db.connection.{DbAdapter, MySQLAdapter}
import db.model.Subjects
import slick.sql.SqlProfile.ColumnOption.SqlType

import scala.concurrent.Future

trait SubjectsRepository extends SubjectsTable { this: DbAdapter =>
  import driver.api._

  /**
    * @param Subjects
    * create new detail
    */
  def create(Subjects: Subjects): Future[Int] = db.run { SubjectsTableAutoInc += Subjects }

  /**
    * @param Subjects
    * update existing user
    */
  def update(Subjects: Subjects): Future[Int] = db.run { SubjectsTableQuery.filter(_.id === Subjects.id.get).update(Subjects) }

  /**
    * @param id
    * Get user by id
    */
  def getById(id: Int): Future[Option[Subjects]] = db.run { SubjectsTableQuery.filter(_.id === id).result.headOption }

  /**
    * @return
    * Get all Subjects
    */
  def getAll(): Future[List[Subjects]] = db.run { SubjectsTableQuery.to[List].result }

  /**
    * @param id
    * delete user by id
    */
  def delete(id: Int): Future[Int] = db.run { SubjectsTableQuery.filter(_.id === id).delete }
}
object SubjectsRepository extends SubjectsRepository with MySQLAdapter

private[repository] trait SubjectsTable { this: DbAdapter =>

  import driver.api._
  private[SubjectsTable] class SubjectsTable(tag: Tag) extends Table[Subjects](tag, "Subjects") {
    val id = column[Int]("Id", O.PrimaryKey, O.AutoInc)

    val category = column[Int]("Category")
    val title = column[String]("Title")
    val schoolYear = column[Int]("SchoolYear")
    val term = column[Int]("Term")
    val units = column[Int]("Units")
    val memo = column[String]("Memo")
    val status = column[Int]("Status")
    val registerDate = column[Timestamp]("RegisterDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
    val editDate = column[Timestamp]("EditDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def * = (id.?, category.?, title, schoolYear, term, units, memo, status, registerDate, editDate) <> (Subjects.tupled, Subjects.unapply)
  }

  protected val SubjectsTableQuery = TableQuery[SubjectsTable]
  protected def SubjectsTableAutoInc = SubjectsTableQuery returning SubjectsTableQuery.map(_.id)
}