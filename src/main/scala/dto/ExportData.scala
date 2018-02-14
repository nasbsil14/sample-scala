package dto

// #*識別フラグ,伝票No.,決算,*取引日付,*借方勘定科目,借方補助科目,借方部門,*借方税区分,*借方金額,借方税金額,*貸方勘定科目,貸方補助科目,貸方部門,*貸方税区分,*貸方金額,貸方税金額,摘要,番号,期日,*タイプ,生成元,仕訳メモ,付箋１,付箋2,*調整
// 2000,,,2016/1/27,地代家賃,,,対象外,141600,,現金,,,対象外,141600,,家賃-駐車場,,,0,,,,,""""""
case class ExportData (
                        flag: String,
                        no: String,
                        settlement: String,
                        tranDate: String,
                        debitAccountItem: String,
                        debitingAssistanceSubjects: String,
                        debitSection: String,
                        debitTaxClass: String,
                        debitAmount: String,
                        debtTaxAmount: String,
                        creditAccountItem: String,
                        creditSubsidiarySubjects: String,
                        creditDepartment: String,
                        creditTaxClass: String,
                        creditAmount: String,
                        creditTaxAmount: String,
                        summary: String,
                        number: String,
                        deadline: String,
                        `type`: String,
                        generationSource: String,
                        journalNotes: String,
                        tag1: String,
                        tag2: String,
                        adjust: String
                      )
