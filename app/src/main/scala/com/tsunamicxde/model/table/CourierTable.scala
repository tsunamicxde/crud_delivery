package com.tsunamicxde.model.table

import com.tsunamicxde.model.entity.Courier
import slick.jdbc.PostgresProfile.api._

class CourierTable(tag: Tag) extends Table[Courier](tag, "couriers") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def surname = column[String]("surname")
  def phone = column[String]("phone")
  def * = (id.?, name, surname, phone) <> ((Courier.apply _).tupled, Courier.unapply)
}

object CourierTable {
  val couriers = TableQuery[CourierTable]
}
