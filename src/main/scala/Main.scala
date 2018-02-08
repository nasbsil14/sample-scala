import service.UsersService

object Main extends App {
  println("main START")

  val list = UsersService.list()
  list.foreach(println)

  println("END")
}
