package service

import scala.concurrent.Future
import scala.language.postfixOps
import model.{AreaModel, GenderModel, JobModel, UserModel}
import repository.{AreasRepository, GendersRepository, JobsRepository, UsersRepository}

trait UserService {
  import scala.concurrent.ExecutionContext.Implicits.global
  def get(id: Int): Future[UserModel] = {
//    for {
//      Some(user) <- UsersRepository.getById(id)
//      Some(gender) <- GendersRepository.getByCode(user.gender)
//      Some(job) <- JobsRepository.getByCode(user.job)
//      Some(area) <- AreasRepository.getByCode(user.areacode)
//    } yield
//      UserModel(Some(user.userid), user.name,
//      Some(GenderModel(gender.gendercode, gender.name.getOrElse(""))),
//      Some(JobModel(job.jobscode, job.name.getOrElse(""))),
//      Some(AreaModel(area.areacode, area.name.getOrElse(""))))

    UsersRepository.getById(id).map {
      {
        case Some(row) => UserModel(Some(row.userid), row.name, None, None, None)
        case _ => UserModel(None, "", None, None, None)
      }
    }
  }
  def list(): Future[Seq[UserModel]] = {
    UsersRepository.getAll().map {result =>
      result.map(row => UserModel(Some(row.userid), row.name, None, None, None))}
  }
}
object UserService extends UserService
