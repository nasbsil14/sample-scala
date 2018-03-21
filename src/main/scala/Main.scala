import java.io.BufferedReader
import java.nio.file.{Files, Paths}

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import scala.collection.JavaConverters._

object Main extends App {
  override def main(args: Array[String]): Unit = {
    println("START")

    val pdDoc: PDDocument = PDDocument.load(Files.newInputStream(Paths.get("/Users/test_user/Desktop/sample.pdf")))
    val stripper: PDFTextStripper = new PDFTextStripper
    //    val writer = Files.newBufferedWriter(Paths.get("/Users/test_user/Desktop/sample_pdf.txt"))
    try {
      stripper.setStartPage(1)
      stripper.setEndPage(1)

//      println(stripper.getText(pdDoc))
      val list = formatStrings(stripper.getText(pdDoc).split("\n").toList)
      println(list.mkString("\n"))
      println(list.size)
      //      stripper.writeText(pdDoc, writer)
      //      writer.flush()
    } finally {
      //      writer.close()
      pdDoc.close()
    }

//    val lines = Files.lines(Paths.get("/Users/test_user/Desktop/test.txt")).iterator().asScala.toList
//    println(formatStrings(lines).mkString("\n"))
//    // val lines = Files.lines(Paths.get("/Users/test_user/Desktop/test.txt")).iterator().asScala.toStream

    println("END")
  }

  def formatStrings(list: List[String]) = {
    list
      .drop(3)
      .map(s => s.split("\\s+").toList)
      //.map(s => {println(s); s})
      .collect({
        case head :: tail if head.length == 1 => tail
        case default => default
      })
      .collect({
        case head :: tail if head.length == 1 => tail
        case default => default
      })
      .collect({
        case head :: tail if head.length == 1 => tail
        case default => default
      })
      .filter(_.nonEmpty)
      .init
      .map(_.map(_.trim).mkString(","))
      .map(_.replace(",A,", "A,").replace(",B,", "B,"))
      .map(_.replace("I", "1").replace("l", "1"))
      .map(_.replace(",·,", ",•,"))
//      .map(_.replace(",·,", ",•,").replace(",・,", ",•,"))
      .map(_.replace(",ｷ,", ",•,"))

//      .filter(s => s.contains("•"))
//      .map(s => s.replace(" •", "•").replace("• ", "•"))
//      .map(s => s.replace(" ー", "ー").replace("ー ", "ー"))
//      .map(s => s.replace(" ・", "・").replace("・ ", "・").replace(" ·", "・").replace("· ", "・"))
//      .map(s => s.replace(" I", " 1").replace(" l", " 1").replace(" I ", " 1 "))
//      .map(s => s.replace("ア ", " ").replace("アB ", " "))
//      .map(s => s.split("\\s+").toList.filter(s => s.matches("[1-9]+") || s.length > 1).mkString(" "))
//      //.map(s => if (s.split("\\s+").length > 6) s.split("\\s+").toList.takeRight(6).tail.mkString(" ") else s)
//      //.filter(s => s.split("\\s+").length > 1)

  }
}
