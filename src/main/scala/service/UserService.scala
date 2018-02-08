package service

import db.test.Tables
import repository.UsersRepository
import model.UserModel
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.{Await, Future}

trait UsersService {
  import scala.concurrent.ExecutionContext.Implicits.global
  def get(id: Int): UserModel = {
    Await.result(UsersRepository.getById(id).map {_ match {
      case Some(row) => UserModel(Some(row.userid), row.name)
      case _ => UserModel(None, "")
    }}, 10 seconds)
  }
  def list(): Seq[UserModel] = {
    Await.result(
      UsersRepository.getAll().map {result =>
      result.map(row => UserModel(Some(row.userid), row.name))
    }, 10 seconds)
  }
}
object UsersService extends UsersService
