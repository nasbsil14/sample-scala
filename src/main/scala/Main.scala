import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.{Files, Paths}
import java.util.stream.Collectors

import dto.{ExportData, ImportData}

import scala.collection.JavaConverters._
import scala.collection.mutable

object Main extends App {
  override def main(args: Array[String]): Unit = {
    println("START")

    if (0 < args.length) {
      val path = Paths.get(args(0))

      if (Files.exists(path) && Files.isRegularFile(path)) {
        val reader = Files.newBufferedReader(path, Charset.forName("SHIFT_JIS"))
//        val reader = Files.newBufferedReader(path)
        val lines: mutable.Buffer[String] = reader.lines().collect(Collectors.toList()).asScala

        lines.map(s => {
//          println(s.replaceAll(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "|").replace("\"\\", "").replace("\"", ""))
          s.replaceAll(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "|").replace("\"\\", "").replace("\"", "").split('|')
        match {
          case Array(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14) =>
            Some(ImportData(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14))
          case _ => None
        }}).filter(_.isDefined).filter(op => safeStringToInt(op.get.useCharge).isDefined).foreach (println)

//        val writer = Files.newBufferedWriter(Paths.get(path.getParent + "/out.csv"))
//        lines.map(s => s.split(",")
//        match {
//          case Array(v1, v2, v3) => Some(CsvData(v1, v2, v3))
//          case _ => None
//        }).filter(_.isDefined).foreach (data => {
//          println(data.get)
//          writer.write(data.get.val3 + "," + data.get.val2 + "," + data.get.val1 + "\n")
//        })
        //      var writer = Files.newBufferedWriter(Paths.get(path.getParent + "/out.csv"))
        //      lines.zipWithIndex.foreach {
        //        case (s: String, i: Int) => {
        //          if (0 < i && i % 1000 == 0) {
        //            writer.flush()
        //            writer = Files.newBufferedWriter(Paths.get(path.getParent + "/out%d.txt".format((i / 1000))))
        //          }
        //          writer.write(s + ",")
        //        }
        //      }

//        writer.flush()
      } else {
        println("read file not exists:" + args(0))
      }
    } else {
      println("read file not exists")
    }

    println("END")
  }

  def safeStringToInt(str: String): Option[Int] = {
    import scala.util.control.Exception._
    catching(classOf[NumberFormatException]) opt str.replace(",", "").toInt
  }
}

case class CsvData (
                   val1: String,
                   val2: String,
                   val3: String
                   )
