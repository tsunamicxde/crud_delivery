package com.tsunamicxde.routes

import com.tsunamicxde.model.JsonFormats
import com.tsunamicxde.model.entity.{Order}
import com.tsunamicxde.service.OrderService

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

class OrderRoutes(orderService: OrderService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonFormats {
  val routes: Route = pathPrefix("orders") {
    concat(
      get {
        path(IntNumber) { id =>
          complete(orderService.getOrder(id).map {
            case Some(orderWithProducts) => orderWithProducts
            case None => throw new NoSuchElementException("Order not found")
          })
        } ~
          pathEndOrSingleSlash {
            complete(orderService.getOrders)
          }
      },
      post {
        entity(as[Order]) { order =>
          parameter("productIds".as[String]) { productIdsParam =>
            val productIds = productIdsParam.split(",").map(_.toInt).toSeq
            complete(orderService.createOrder(order, productIds).map(_.toString))
          }
        }
      },
      put {
        path(IntNumber) { id =>
          entity(as[Order]) { updatedOrder =>
            complete(orderService.updateOrder(id, updatedOrder).map(_.toString))
          }
        }
      },
      delete {
        path(IntNumber) { id =>
          complete(orderService.deleteOrder(id).map(_.toString))
        }
      }
    )
  }
}
