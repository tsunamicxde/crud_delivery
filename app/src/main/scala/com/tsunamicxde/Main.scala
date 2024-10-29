package com.tsunamicxde

import com.tsunamicxde.routes.{CategoryRoutes, CourierRoutes, OrderRoutes, ProductRoutes, UserRoutes}
import com.tsunamicxde.service.{CategoryService, CourierService, OrderService, ProductService, UserService}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
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

  val courierService = new CourierService(db)
  val courierRoutes = new CourierRoutes(courierService)

  val categoryService = new CategoryService(db)
  val categoryRoutes = new CategoryRoutes(categoryService)

  val productService = new ProductService(db)
  val productRoutes = new ProductRoutes(productService)

  val orderService = new OrderService(db)
  val orderRoutes = new OrderRoutes(orderService)

  val routes = userRoutes.routes ~ courierRoutes.routes ~ categoryRoutes.routes ~ productRoutes.routes ~ orderRoutes.routes

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

  println("Server online at http://localhost:8080/")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
