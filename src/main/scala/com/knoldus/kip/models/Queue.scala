package com.knoldus.kip.models

class Queue(queueWithList: List[Int]) {
  def dequeue: Queue = {
    queueWithList match {
      case _ :: _ => new Queue(queueWithList.tail)
      case _ => throw new RuntimeException
    }
  }
  def enqueue(num : Int): Queue = {
    new Queue(queueWithList ::: List(num))
  }
}
object MyQueue extends App {
  val q1 =new Queue(List(1))
  val q2 = q1.dequeue
}