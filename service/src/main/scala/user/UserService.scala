package user

import scala.concurrent.Future
import scala.language.postfixOps

trait UsersService {
  def get(id: Int): Future[UserModel] = {
    UsersRepository.getById(id).map {
      {
        case Some(row) => UserModel(Some(row.userid), row.name)
        case _ => UserModel(None, "")
      }
    }
  }
  def list(): Future[Seq[UserModel]] = {
    UsersRepository.getAll().map {result =>
      result.map(row => UserModel(Some(row.userid), row.name))}
  }
}
object UsersService extends UsersService
