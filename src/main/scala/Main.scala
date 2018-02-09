
import service.UserService

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  println("main START")

  val list = UserService.list()
  list.foreach(println)

  println(Await.result(UserService.get(1), Duration.Inf))

  println("END")
}
