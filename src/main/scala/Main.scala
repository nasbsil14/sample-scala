import java.io.BufferedReader
import java.nio.file.{Files, Paths}

import db.model.Subjects
import db.repository.SubjectsRepository
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object Main extends App {
  override def main(args: Array[String]): Unit = {
    println("START")

    if (false) {
      val pdDoc: PDDocument = PDDocument.load(Files.newInputStream(Paths.get("/Users/test_user/Desktop/sample_pdf.pdf")))
      val stripper: PDFTextStripper = new PDFTextStripper

      val writer = Files.newBufferedWriter(Paths.get("/Users/test_user/Desktop/sample_pdf.txt"))
      try {
        stripper.setStartPage(89)
        stripper.setEndPage(96)

        //      println(stripper.getText(pdDoc))
        val list = formatStrings3(stripper.getText(pdDoc).split("\n").toList)
        println(list)
        //      println(list.size)

        list.foreach(s => {
          writer.write(s)
          writer.newLine()
        })
        //      writer.flush()
        //      //      stripper.writeText(pdDoc, writer)
      } finally {
        writer.close()
        pdDoc.close()
      }
    }

    val f = SubjectsRepository.getAll()
//    Await.ready(f, Duration.Inf)
//    f.foreach(println)
    val res = Await.result(f, Duration.Inf)
    res.foreach(println)
//    SubjectsRepository.getAll().onComplete {
//      case Success(list) => println(list)
//      case Failure(e) => println(e)
//    }
//    Thread.sleep(1000)

//    val lines = Files.lines(Paths.get("/Users/test_user/Desktop/test.txt")).iterator().asScala.toList
//    println(formatStrings(lines).mkString("\n"))
//    // val lines = Files.lines(Paths.get("/Users/test_user/Desktop/test.txt")).iterator().asScala.toStream

    println("END")
  }

  def formatStrings1(list: List[String]) = {
    list
      .filter(s => s.contains("•"))
      .map(s => s.replace(" •", "•").replace("• ", "•"))
      .map(s => s.replace(" ー", "ー").replace("ー ", "ー"))
      .map(s => s.replace(" ・", "・").replace("・ ", "・").replace(" ·", "・").replace("· ", "・"))
      .map(s => s.replace(" I", " 1").replace(" l", " 1").replace(" I ", " 1 "))
      .map(s => s.replace("ア ", " ").replace("アB ", " "))
      .map(s => s.split("\\s+").toList.filter(s => s.matches("[1-9]+") || s.length > 1).mkString(" "))
    //.map(s => if (s.split("\\s+").length > 6) s.split("\\s+").toList.takeRight(6).tail.mkString(" ") else s)
    //.filter(s => s.split("\\s+").length > 1)

  }

  def formatStrings2(list: List[String]) = {
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
      .map(_.replace("l", "1"))
      //      .map(_.replace("I", "1").replace("l", "1"))
      .map(_.replace(",·,", ",•,"))
      //      .map(_.replace(",·,", ",•,").replace(",・,", ",•,"))
      .map(_.replace(",ｷ,", ",•,"))
  }

  def formatStrings3(list: List[String]) = {
    list
      .map(s => s.split("\\s").toList)
      .filter(list => list.size == 6)
      .map(list => {
        Nil :+ list.head :+ list(1).head :+ list(1).tail :+ list(2) :+ list(3) :+ list(4) :+ list(5)
      })
      .map(_.mkString(","))
  }

  def importData(): Unit = {
    val lines = Files.lines(Paths.get("/Users/test_user/Desktop/test.txt")).iterator().asScala.toList
    lines
      .map(s => s.split(",").toList)
      .foreach(list => {
        val class1: Option[Int] = if (!list(0).isEmpty) Some(list(0).toInt) else None
        val class2: Option[Int] = if (!list(1).isEmpty) Some(list(1).toInt) else None
        val class3: Option[Int] = if (!list(2).isEmpty) Some(list(2).toInt) else None
        val title: String = list(3)
        val schoolYear: Int = list(4).toInt

        val units: Int = list(6).toInt
        val memo: String = list.splitAt(7)._2.mkString

        val rgTerm1 = """(.+)・(.+)""".r
        val rgTerm2 = """(.+)～(.+)""".r
        list(5).replace("①", "1").replace("②", "2").replace("③", "3").replace("④", "4") match {
          case rgTerm1(v1, v2) => {
            SubjectsRepository.create(new Subjects(None, class1, class2, class3, title, schoolYear, v1.toInt, units, memo, null, null, null))
            SubjectsRepository.create(new Subjects(None, class1, class2, class3, title, schoolYear, v2.toInt, units, memo, null, null, null))
          }
          case rgTerm2(v1, v2) => {
            (v1.toInt to v2.toInt).foreach(i => {
              SubjectsRepository.create(new Subjects(None, class1, class2, class3, title, schoolYear, i, units, memo, null, null, null))
            })
          }
          case _ => {
            val term: Int = list(5).toInt
            SubjectsRepository.create(new Subjects(None, class1, class2, class3, title, schoolYear, term, units, memo, null, null, null))
          }
        }
      }
    )
  }
}
