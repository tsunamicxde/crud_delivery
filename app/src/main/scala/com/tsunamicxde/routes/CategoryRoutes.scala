package com.tsunamicxde.routes

import com.tsunamicxde.model.JsonFormats
import com.tsunamicxde.model.entity.Category
import com.tsunamicxde.service.CategoryService

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

class CategoryRoutes(categoryService: CategoryService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonFormats {
  val routes: Route = pathPrefix("categories") {
    concat(
      post {
        entity(as[Category]) { category =>
          complete(categoryService.createCategory(category).map(_.toString))
        }
      },
      get {
        path(IntNumber) { id =>
          complete(categoryService.getCategory(id))
        } ~
          pathEndOrSingleSlash {
            complete(categoryService.getCategories())
          }
      },
      put {
        path(IntNumber) { id =>
          entity(as[Category]) { updatedCategory =>
            complete(categoryService.updateCategory(id, updatedCategory).map(_.toString))
          }
        }
      },
      delete {
        path(IntNumber) { id =>
          complete(categoryService.deleteCategory(id).map(_.toString))
        }
      }
    )
  }
}
