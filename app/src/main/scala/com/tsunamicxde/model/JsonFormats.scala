package com.tsunamicxde.model

import com.tsunamicxde.model.entity.{Category, CategoryWithProducts, Courier, Order, OrderProduct, OrderWithProducts, Product, User}
import spray.json._

trait JsonFormats extends DefaultJsonProtocol {
  implicit val userFormat: RootJsonFormat[User] = jsonFormat5(User)
  implicit val courierFormat: RootJsonFormat[Courier] = jsonFormat4(Courier)
  implicit val productFormat: RootJsonFormat[Product] = jsonFormat4(Product)
  implicit val categoryFormat: RootJsonFormat[Category] = jsonFormat2(Category)
  implicit val categoryWithProductsFormat: RootJsonFormat[CategoryWithProducts] = jsonFormat2(CategoryWithProducts)
  implicit val orderFormat: RootJsonFormat[Order] = jsonFormat3(Order)
  implicit val orderProductFormat: RootJsonFormat[OrderProduct] = jsonFormat2(OrderProduct)
  implicit val orderWithProductsFormat: RootJsonFormat[OrderWithProducts] = jsonFormat5(OrderWithProducts)
}
