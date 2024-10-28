package com.tsunamicxde

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.tsunamicxde.model.{JsonFormats, User}

import scala.concurrent.ExecutionContext

class UserRoutes(userService: UserService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonFormats {
  val routes: Route = pathPrefix("users") {
    concat(
      post {
        entity(as[User]) { user =>
          complete(userService.createUser(user).map(_.toString))
        }
      },
      get {
        path(IntNumber) { id =>
          complete(userService.getUser(id))
        } ~
          pathEndOrSingleSlash {
            complete(userService.getUsers())
          }
      },
      put {
        path(IntNumber) { id =>
          entity(as[User]) { updatedUser =>
            complete(userService.updateUser(id, updatedUser).map(_.toString))
          }
        }
      },
      delete {
        path(IntNumber) { id =>
          complete(userService.deleteUser(id).map(_.toString))
        }
      }
    )
  }
}
