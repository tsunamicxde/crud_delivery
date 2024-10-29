package com.tsunamicxde.model.table

import com.tsunamicxde.model.entity.Order

import slick.jdbc.PostgresProfile.api._

class OrderTable(tag: Tag) extends Table[Order](tag, "orders") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def userId = column[Int]("user_id")
  def courierId = column[Int]("courier_id")

  def * = (id.?, userId, courierId) <> ((Order.apply _).tupled, Order.unapply)
}

object OrderTable {
  val orders = TableQuery[OrderTable]
}
