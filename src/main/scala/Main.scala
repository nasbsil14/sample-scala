
import user.UsersService

import scala.concurrent.ExecutionContext.Implicits.global
object Main extends App {
  println("main START")

  val list = UsersService.list()
  list.foreach(println)

  println("END")
}
