package com.tsunamicxde.model

import slick.jdbc.PostgresProfile.api._

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def surname = column[String]("surname")
  def phone = column[String]("phone")
  def address = column[String]("address")
  def * = (id.?, name, surname, phone, address) <> ((User.apply _).tupled, User.unapply)
}

object UserTable {
  val users = TableQuery[UserTable]
}
