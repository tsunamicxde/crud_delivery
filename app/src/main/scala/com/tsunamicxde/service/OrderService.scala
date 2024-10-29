package com.tsunamicxde.service

import com.tsunamicxde.model.entity.{Order, OrderProduct, OrderWithProducts}
import com.tsunamicxde.model.table.{CourierTable, OrderProductTable, OrderTable, ProductTable, UserTable}

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class OrderService(db: Database)(implicit ec: ExecutionContext) {

  def getOrders: Future[Seq[OrderWithProducts]] = {
    for {
      orders <- db.run(OrderTable.orders.result)
      ordersWithProducts <- Future.sequence(orders.map { order =>
        for {
          productIds <- db.run(OrderProductTable.orderProducts.filter(_.orderId === order.id.get).map(_.productId).result)
          products <- db.run(ProductTable.products.filter(_.id.inSet(productIds)).result)
          totalPrice = products.map(_.price).sum
          courier <- db.run(CourierTable.couriers.filter(_.id === order.courierId).result.headOption)
          user <- db.run(UserTable.users.filter(_.id === order.userId).result.headOption)
        } yield OrderWithProducts(order, products, courier.getOrElse(null), user.getOrElse(null), totalPrice)
      })
    } yield ordersWithProducts
  }

  def getOrder(id: Int): Future[Option[OrderWithProducts]] = {
    for {
      orderOpt <- db.run(OrderTable.orders.filter(_.id === id).result.headOption)
      orderWithProductsOpt <- orderOpt match {
        case Some(order) =>
          for {
            productIds <- db.run(OrderProductTable.orderProducts.filter(_.orderId === order.id.get).map(_.productId).result)
            products <- db.run(ProductTable.products.filter(_.id.inSet(productIds)).result)
            totalPrice = products.map(_.price).sum
            courierOpt <- db.run(CourierTable.couriers.filter(_.id === order.courierId).result.headOption)
            userOpt <- db.run(UserTable.users.filter(_.id === order.userId).result.headOption)
          } yield {
            val courier = courierOpt.getOrElse(null)
            val user = userOpt.getOrElse(null)
            Some(OrderWithProducts(order, products, courier, user, totalPrice))
          }
        case None => Future.successful(None)
      }
    } yield orderWithProductsOpt
  }

  def createOrder(order: Order, productIds: Seq[Int]): Future[Int] = {
    val orderIdFuture = db.run(OrderTable.orders returning OrderTable.orders.map(_.id) += order)

    orderIdFuture.flatMap { orderId =>
      val orderProducts = productIds.map(productId => OrderProduct(orderId, productId))
      db.run(OrderProductTable.orderProducts ++= orderProducts).map(_ => orderId)
    }
  }

  def updateOrder(id: Int, updatedOrder: Order): Future[Int] = {
    db.run(OrderTable.orders.filter(_.id === id)
      .map(order => (order.userId, order.courierId))
      .update((updatedOrder.userId, updatedOrder.courierId)))
  }

  def deleteOrder(id: Int): Future[Int] = {
    db.run(OrderTable.orders.filter(_.id === id).delete)
  }
}
