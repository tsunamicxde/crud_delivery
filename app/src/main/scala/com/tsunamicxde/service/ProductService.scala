package com.tsunamicxde.service

import com.tsunamicxde.model.entity.Product
import com.tsunamicxde.model.table.ProductTable
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class ProductService(db: Database)(implicit ec: ExecutionContext) {

  def getProducts(): Future[Seq[Product]] = {
    db.run(ProductTable.products.result)
  }

  def getProduct(id: Int): Future[Option[Product]] = {
    db.run(ProductTable.products.filter(_.id === id).result.headOption)
  }

  def createProduct(product: Product): Future[Int] = {
    db.run(ProductTable.products += product)
  }

  def updateProduct(id: Int, updatedProduct: Product): Future[Int] = {
    db.run(ProductTable.products.filter(_.id === id).map(product => (product.name, product.price, product.categoryId))
      .update((updatedProduct.name, updatedProduct.price, updatedProduct.categoryId)))
  }

  def deleteProduct(id: Int): Future[Int] = {
    db.run(ProductTable.products.filter(_.id === id).delete)
  }
}
