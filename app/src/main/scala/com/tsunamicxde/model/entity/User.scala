package com.tsunamicxde.model.entity

case class User(id: Option[Int] = None, name: String, surname: String, phone: String, address: String) extends Person
