package db.model

import java.sql.Timestamp

case class OricoDetail(
                  id: Option[Int] = None,
                  TranDate: String,
                  SuppliersInfo: String,
                  OldNew: String,
                  User: String,
                  PayStartDate: String,
                  PayClass: String,
                  PayCount: Int,
                  CurrentCount: Int,
                  UseCharge: Int,
                  Fee: Int,
                  Other: String,
                  MonthCharge: Int,
                  CarriedForwardBalance: Int,
                  ExpIgnore: Int,
                  status: Int,
                  registerDate: Timestamp,
                  updateDate: Timestamp
                )
