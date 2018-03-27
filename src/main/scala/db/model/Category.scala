package db.model

import java.sql.Timestamp

case class Category(
                  id: Option[Int] = None,
                  no: Int,
                  title: String,
                  status: Int,
                  registerDate: Timestamp,
                  updateDate: Timestamp
                )
