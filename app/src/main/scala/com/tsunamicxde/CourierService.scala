package com.tsunamicxde

import com.tsunamicxde.model.{Courier, CourierTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class CourierService(db: Database)(implicit ec: ExecutionContext) {

  def getCouriers(): Future[Seq[Courier]] = {
    db.run(CourierTable.couriers.result)
  }

  def getCourier(id: Int): Future[Option[Courier]] = {
    db.run(CourierTable.couriers.filter(_.id === id).result.headOption)
  }

  def createCourier(courier: Courier): Future[Int] = {
    db.run(CourierTable.couriers += courier)
  }

  def updateCourier(id: Int, updatedCourier: Courier): Future[Int] = {
    db.run(CourierTable.couriers.filter(_.id === id).map(courier => (courier.name, courier.surname, courier.phone))
      .update((updatedCourier.name, updatedCourier.surname, updatedCourier.phone)))
  }

  def deleteCourier(id: Int): Future[Int] = {
    db.run(CourierTable.couriers.filter(_.id === id).delete)
  }
}
