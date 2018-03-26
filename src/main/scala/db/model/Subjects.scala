package db.model

import java.sql.Timestamp

case class Subjects(
                  id: Option[Int] = None,
                  class1: Option[Int],
                  class2: Option[Int],
                  class3: Option[Int],
                  title: String,
                  schoolYear: Int,
                  term: Int,
                  units: Int,
                  memo: String,
                  status: Int,
                  registerDate: Timestamp,
                  editDate: Timestamp
                )
