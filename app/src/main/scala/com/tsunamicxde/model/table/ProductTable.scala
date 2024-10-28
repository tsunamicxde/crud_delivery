package com.tsunamicxde.model.table

import com.tsunamicxde.model.entity.Product
import slick.jdbc.PostgresProfile.api._

class ProductTable(tag: Tag) extends Table[Product](tag, "products") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def price = column[Double]("price")
  def categoryId = column[Option[Int]]("category_id")

  def * = (id.?, name, price, categoryId) <> ((Product.apply _).tupled, Product.unapply)
}

object ProductTable {
  val products = TableQuery[ProductTable]
}
