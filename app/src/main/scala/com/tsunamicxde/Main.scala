package com.tsunamicxde

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("UserApiSystem")
  implicit val materializer: Materializer = Materializer(system)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val db = Database.forConfig("db")
  val userService = new UserService(db)
  val userRoutes = new UserRoutes(userService)

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(userRoutes.routes)

  println("Server online at http://localhost:8080/")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
