
import user.UserService

import scala.concurrent.ExecutionContext.Implicits.global
object Main extends App {
  println("main START")

  val list = UserService.list()
  list.foreach(println)

  println("END")
}
