package com.tsunamicxde.model.table

import com.tsunamicxde.model.entity.Category
import slick.jdbc.PostgresProfile.api._

class CategoryTable(tag: Tag) extends Table[Category](tag, "categories") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id.?, name) <> ((Category.apply _).tupled, Category.unapply)
}

object CategoryTable {
  val categories = TableQuery[CategoryTable]
}
