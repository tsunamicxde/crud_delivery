package com.tsunamicxde.model

trait Person {
  def id: Option[Int]
  def name: String
  def surname: String
  def phone: String
}