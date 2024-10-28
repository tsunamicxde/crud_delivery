package com.tsunamicxde.model

import spray.json._

trait JsonFormats extends DefaultJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat5(User)
  implicit val courierFormat: RootJsonFormat[Courier] = jsonFormat4(Courier)
}
