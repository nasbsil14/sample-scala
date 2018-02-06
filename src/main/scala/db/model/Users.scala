package db.model

import java.sql.Timestamp

case class Users(
                  userId: Option[Int] = None,
                  name: String,
                  gender: Int,
                  birthDay: Timestamp,
                  job: Int,
                  areaCode: Int,
                  status: Int,
                  registerDate: Timestamp,
                  updateDate: Timestamp
                )
