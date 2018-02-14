package dto

// ご利用日,ご利用先など,新旧,ご利用者,支払開始年月,支払区分,お支払回数,何回目,ご利用金額,手数料・利息,年利%,その他,当月ご請求額,翌月繰越残高
// 2016年2月分,ミニミニ城北　家賃等,*,本人,2016年1月,アド,1,1,"\141,600","\1,416",,"\0","\143,016","\0"
case class ImportData (
                      tranDate: String,
                      suppliersInfo: String,
                      oldNew: String,
                      user: String,
                      payStartDate: String,
                      payClass: String,
                      payCount: String,
                      currentCount: String,
                      useCharge: String,
                      fee: String,
                      rate: String,
                      other: String,
                      monthCharge: String,
                      carriedForwardBalance: String
                      )
