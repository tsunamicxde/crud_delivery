package com.tsunamicxde.model.table

import com.tsunamicxde.model.entity.OrderProduct

import slick.jdbc.PostgresProfile.api._

class OrderProductTable(tag: Tag) extends Table[OrderProduct](tag, "order_product") {
  def orderId = column[Int]("order_id")
  def productId = column[Int]("product_id")

  def * = (orderId, productId) <> ((OrderProduct.apply _).tupled, OrderProduct.unapply)
}

object OrderProductTable {
  val orderProducts = TableQuery[OrderProductTable]
}
