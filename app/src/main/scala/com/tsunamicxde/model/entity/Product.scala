package com.tsunamicxde.model.entity

case class Product(id: Option[Int] = None, name: String, price: Double, categoryId: Option[Int] = None)
