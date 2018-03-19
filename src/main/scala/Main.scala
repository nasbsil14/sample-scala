import java.nio.file.{Files, Paths}

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

object Main extends App {
  override def main(args: Array[String]): Unit = {
    println("START")

    val pdDoc: PDDocument = PDDocument.load(Files.newInputStream(Paths.get("/Users/test_user/Desktop/sample.pdf")))
    val stripper: PDFTextStripper = new PDFTextStripper
    //    val writer = Files.newBufferedWriter(Paths.get("/Users/b08455/Desktop/sample_pdf.txt"))
    try {
      stripper.setStartPage(1)
      stripper.setEndPage(2)
      println(stripper.getText(pdDoc))
      //      stripper.writeText(pdDoc, writer)
      //      writer.flush()
    } finally {
      //      writer.close()
      pdDoc.close()
    }
    println("END")
  }
}
