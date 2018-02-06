import com.typesafe.config.{Config, ConfigFactory}
import slick.codegen.SourceCodeGenerator

object Main extends App {
  val conf: Config = ConfigFactory.load()
  SourceCodeGenerator.main (
    Array("slick.jdbc.MySQLProfile",
      conf.getString("mysql.driver"),
      conf.getString("mysql.url"),
      conf.getString("codegen.outFolder"),
      conf.getString("codegen.pkg"),
      conf.getString("mysql.user"),
      conf.getString("mysql.password"))
  )
}
