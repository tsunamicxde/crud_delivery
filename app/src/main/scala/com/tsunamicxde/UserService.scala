package com.tsunamicxde

import com.tsunamicxde.model.{User, UserTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class UserService(db: Database)(implicit ec: ExecutionContext) {

  def getUsers(): Future[Seq[User]] = {
    db.run(UserTable.users.result)
  }

  def getUser(id: Int): Future[Option[User]] = {
    db.run(UserTable.users.filter(_.id === id).result.headOption)
  }

  def createUser(user: User): Future[Int] = {
    db.run(UserTable.users += user)
  }

  def updateUser(id: Int, updatedUser: User): Future[Int] = {
    db.run(UserTable.users.filter(_.id === id).map(user => (user.name, user.surname, user.phone, user.address))
      .update((updatedUser.name, updatedUser.surname, updatedUser.phone, updatedUser.address)))
  }

  def deleteUser(id: Int): Future[Int] = {
    db.run(UserTable.users.filter(_.id === id).delete)
  }
}
