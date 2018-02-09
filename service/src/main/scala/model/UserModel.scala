package model

case class UserModel (
                     id: Option[Int] = None,
                     name: String,
                     gender: Option[GenderModel],
                     job: Option[JobModel],
                     area: Option[AreaModel]
                     )
