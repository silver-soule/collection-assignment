package com.knoldus.kip.models

trait Queue {
  val queueWithList: List[Int]
  def enqueue: Boolean
  def dequeue: Int
}
