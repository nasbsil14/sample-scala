package db

import java.sql.Timestamp
import java.time.LocalDateTime

import db.model._
import db.repository._

import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

//  val userId = UsersRepository.create(Users(None, "", 0, Timestamp.valueOf(LocalDateTime.now()), 0, 0, 0, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now())))
//
//  userId.onComplete {
//    case Success(id) =>
//      println(id)
//      // UserOptRepository.create(UserOpt("option"))
//    case _ => println("Error ...........")
//  }
//
//  UsersRepository.getAll().foreach(println)
  OricoDetailRepository.getAll().foreach(println)

  Thread.sleep(10 * 1000)

}
