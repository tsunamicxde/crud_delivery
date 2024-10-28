package com.tsunamicxde.model

import com.tsunamicxde.model.entity.{Courier, Product, User, Category, CategoryWithProducts}
import spray.json._

trait JsonFormats extends DefaultJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat5(User)
  implicit val courierFormat: RootJsonFormat[Courier] = jsonFormat4(Courier)
  implicit val productFormat: RootJsonFormat[Product] = jsonFormat4(Product)
  implicit val categoryFormat: RootJsonFormat[Category] = jsonFormat2(Category)
  implicit val categoryWithProductsFormat: RootJsonFormat[CategoryWithProducts] = jsonFormat2(CategoryWithProducts)
}
