package com.tsunamicxde.routes

import com.tsunamicxde.model.JsonFormats
import com.tsunamicxde.model.entity.Courier
import com.tsunamicxde.service.CourierService

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer

import scala.concurrent.ExecutionContext

class CourierRoutes(courierService: CourierService)(implicit ec: ExecutionContext, mat: Materializer) extends JsonFormats {
  val routes: Route = pathPrefix("couriers") {
    concat(
      post {
        entity(as[Courier]) { courier =>
          complete(courierService.createCourier(courier).map(_.toString))
        }
      },
      get {
        path(IntNumber) { id =>
          complete(courierService.getCourier(id))
        } ~
          pathEndOrSingleSlash {
            complete(courierService.getCouriers())
          }
      },
      put {
        path(IntNumber) { id =>
          entity(as[Courier]) { updatedCourier =>
            complete(courierService.updateCourier(id, updatedCourier).map(_.toString))
          }
        }
      },
      delete {
        path(IntNumber) { id =>
          complete(courierService.deleteCourier(id).map(_.toString))
        }
      }
    )
  }
}
