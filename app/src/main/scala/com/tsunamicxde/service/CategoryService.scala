package com.tsunamicxde.service

import com.tsunamicxde.model.entity.{Category, CategoryWithProducts}
import com.tsunamicxde.model.table.{CategoryTable, ProductTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class CategoryService(db: Database)(implicit ec: ExecutionContext) {

  def getCategoriesWithProducts(): Future[Seq[CategoryWithProducts]] = {
    val query = for {
      (category, product) <- CategoryTable.categories joinLeft ProductTable.products on (_.id === _.categoryId)
    } yield (category, product)

    db.run(query.result).map { result =>
      result
        .groupBy(_._1)
        .map { case (categoryRow, products) =>
          val category = Category(categoryRow.id, categoryRow.name)
          val productSeq = products.flatMap(_._2)
          CategoryWithProducts(category, productSeq)
        }
        .toSeq
    }
  }

  def getCategories(): Future[Seq[CategoryWithProducts]] = {
    getCategoriesWithProducts()
  }

  def getCategory(id: Int): Future[Option[CategoryWithProducts]] = {
    getCategoriesWithProducts().map(_.find(_.category.id.contains(id)))
  }

  def getProductsByCategoryId(categoryId: Int): Future[Seq[Product]] = {
    db.run(ProductTable.products.filter(_.categoryId === categoryId).result)
  }

  def createCategory(category: Category): Future[Int] = {
    db.run(CategoryTable.categories += category)
  }

  def updateCategory(id: Int, updatedCategory: Category): Future[Int] = {
    db.run(CategoryTable.categories.filter(_.id === id).map(category => (category.name))
      .update((updatedCategory.name)))
  }

  def deleteCategory(id: Int): Future[Int] = {
    db.run(CategoryTable.categories.filter(_.id === id).delete)
  }
}
