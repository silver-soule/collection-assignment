package com.knoldus.kip.service

import com.knoldus.kip.models.Student

trait ListAssignment {

  //Assignment -1
  def failedStudents(subjectId: Long, percentage: Double, passOrFail: String): Int = ???
  def topBottomStudents(subjectId: Long, count: Int, topOrBottom: String): List[Student] = ???
  def topAndLeastScorers(topOrBottom: String, count: Int): List[Student] = ???
  def getScholarshipGroups(percentage: Float, goodScholarship: Int, normalScholarship: Int)
  : (List[(Student, Int)], List[(Student, Int)]) = ???
  def passedOrFailed(passOrFail: String, percentage: Float): List[Student] = ???
  def studentsWithMoreThan95: List[Student] = ???
  def generateReport: List[(String, List[Int])] = ??? //Must use the groupBy() Method of the List

  //Assignment - 2
  def getLastElementWithIndex(list: List[String]): (String, Int) = ???
  def printTable(list: List[Long]): List[Long] = ???
  def aggregateLists(list1: List[String], list2: List[Long]): List[List[(String, Long)]] = ???
  def getSumOfList(list: List[Long]): Long = ???
  def getMultiplicationOfList(list: List[Long]) : Long = ???
  def quickSortList(list: List[Long]): Long = ???
  def mergeSortList(list: List[Long]): Long = ???

}
