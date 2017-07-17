package com.knoldus.kip.models

class Stack(stackWithList:List[Int]) {
  def pop: Stack = {
    stackWithList match {
      case _ ::_ => new Stack (stackWithList.tail)
      case _ => throw new RuntimeException
    }
  }
  def push(h:Int): Stack = {
    new Stack(h::stackWithList)
  }
}

