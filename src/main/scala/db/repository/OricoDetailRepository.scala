package db.repository

import java.sql.Timestamp

import db.connection.{DbAdapter, MySQLAdapter}
import db.model.OricoDetail
import slick.sql.SqlProfile.ColumnOption.SqlType

import scala.concurrent.Future

trait OricoDetailRepository extends OricoDetailTable { this: DbAdapter =>
  import driver.api._

  /**
    * @param oricoDetail
    * create new detail
    */
  def create(oricoDetail: OricoDetail): Future[Int] = db.run { oricoDetailTableAutoInc += oricoDetail }

  /**
    * @param oricoDetail
    * update existing user
    */
  def update(oricoDetail: OricoDetail): Future[Int] = db.run { oricoDetailTableQuery.filter(_.id === oricoDetail.id.get).update(oricoDetail) }

  /**
    * @param id
    * Get user by id
    */
  def getById(id: Int): Future[Option[OricoDetail]] = db.run { oricoDetailTableQuery.filter(_.id === id).result.headOption }

  /**
    * @return
    * Get all oricoDetail
    */
  def getAll(): Future[List[OricoDetail]] = db.run { oricoDetailTableQuery.to[List].result }

  /**
    * @param id
    * delete user by id
    */
  def delete(id: Int): Future[Int] = db.run { oricoDetailTableQuery.filter(_.id === id).delete }
}
object OricoDetailRepository extends OricoDetailRepository with MySQLAdapter

private[repository] trait OricoDetailTable { this: DbAdapter =>

  import driver.api._
  private[OricoDetailTable] class OricoDetailTable(tag: Tag) extends Table[OricoDetail](tag, "OricoDetail") {
    val id = column[Int]("Id", O.PrimaryKey, O.AutoInc)

    val tranDate = column[String]("TranDate")
    val suppliersInfo = column[String]("SuppliersInfo")
    val oldNew = column[String]("OldNew")
    val user = column[String]("User")
    val payStartDate = column[String]("PayStartDate")
    val payClass = column[String]("PayClass")
    val payCount = column[Int]("PayCount")
    val currentCount = column[Int]("CurrentCount")
    val useCharge = column[Int]("UseCharge")
    val fee = column[Int]("Fee")
    val rate = column[Float]("Rate")
    val other = column[String]("Other")
    val monthCharge = column[Int]("MonthCharge")
    val carriedForwardBalance = column[Int]("CarriedForwardBalance")
    val expIgnore = column[Int]("ExpIgnore")

    val status = column[Int]("Status")
    val registerDate = column[Timestamp]("RegisterDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))
    val updateDate = column[Timestamp]("UpdateDate", SqlType("timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP"))

    def * = (id.?, tranDate, suppliersInfo, oldNew, user, payStartDate, payClass, payCount, currentCount, useCharge
      , fee, rate, other, monthCharge, carriedForwardBalance, expIgnore, status, registerDate, updateDate) <> (OricoDetail.tupled, OricoDetail.unapply)

  }

  protected val oricoDetailTableQuery = TableQuery[OricoDetailTable]
  protected def oricoDetailTableAutoInc = oricoDetailTableQuery returning oricoDetailTableQuery.map(_.id)
}