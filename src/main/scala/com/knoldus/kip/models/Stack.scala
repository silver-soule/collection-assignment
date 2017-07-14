package com.knoldus.kip.models

trait Stack[Int] {
  val stackWithList : List[Int]
  def pop: Int = ???
  def push: Int = ??? //returns the top after pushing the element on the stack
}
