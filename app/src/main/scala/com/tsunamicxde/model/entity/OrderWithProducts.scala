package com.tsunamicxde.model.entity

case class OrderWithProducts(
                              order: Order,
                              products: Seq[Product],
                              courier: Courier,
                              user: User,
                              totalPrice: Double
                            )
