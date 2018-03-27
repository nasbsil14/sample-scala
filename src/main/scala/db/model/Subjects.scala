package db.model

import java.sql.Timestamp

case class Subjects(
                  id: Option[Int] = None,
                  category: Option[Int],
                  title: String,
                  schoolYear: Int,
                  term: Int,
                  units: Int,
                  memo: String,
                  status: Int,
                  registerDate: Timestamp,
                  editDate: Timestamp
                )
