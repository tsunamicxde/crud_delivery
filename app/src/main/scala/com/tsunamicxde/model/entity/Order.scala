package com.tsunamicxde.model.entity

case class Order (
                    id: Option[Int] = None,
                    userId: Int,
                    courierId: Int
                 )