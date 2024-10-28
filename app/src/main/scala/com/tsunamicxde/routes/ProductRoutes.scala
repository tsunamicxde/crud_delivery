package com.tsunamicxde.routes

import com.tsunamicxde.model.JsonFormats
import com.tsunamicxde.model.entity.Product
import com.tsunamicxde.service.ProductService

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

class ProductRoutes(productService: ProductService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonFormats {
  val routes: Route = pathPrefix("products") {
    concat(
      post {
        entity(as[Product]) { product =>
          complete(productService.createProduct(product).map(_.toString))
        }
      },
      get {
        path(IntNumber) { id =>
          complete(productService.getProduct(id))
        } ~
          pathEndOrSingleSlash {
            complete(productService.getProducts())
          }
      },
      put {
        path(IntNumber) { id =>
          entity(as[Product]) { updatedProduct =>
            complete(productService.updateProduct(id, updatedProduct).map(_.toString))
          }
        }
      },
      delete {
        path(IntNumber) { id =>
          complete(productService.deleteProduct(id).map(_.toString))
        }
      }
    )
  }
}
