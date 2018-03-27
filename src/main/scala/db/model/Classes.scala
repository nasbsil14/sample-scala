package db.model

import java.sql.Timestamp

case class Classes(
                  id: Option[Int] = None,
                  no: Int,
                  title: String,
                  status: Int,
                  registerDate: Timestamp,
                  updateDate: Timestamp
                )
