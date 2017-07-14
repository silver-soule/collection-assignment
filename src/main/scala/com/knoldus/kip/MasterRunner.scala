package com.knoldus.kip

import com.knoldus.kip.models.{ScoreCard, Student}

object MasterRunner extends App {

  //List Based - Assignment 1
  def failedStudents(subjectId: Long, percentage: Double, passOrFail: String): Int = ???
  def topBottomStudents(subjectId: Long, count: Int, topOrBottom: String): List[Student] = ???
  def topAndLeastScorers(topOrBottom: String, count: Int): List[Student] = ???
  def getScholarshipGroups(percentage: Float, goodScholarship: Int, normalScholarship: Int)
    : (List[(Student, Int)], List[(Student, Int)]) = ???
  def passedOrFailed(passOrFail: String, percentage: Float): List[Student] = ???
  def studentsWithMoreThan95: List[Student] = ???
  def generateReport: List[(String, List[Int])] = ??? //Must use the groupBy() Method of the List


  //Collection Based - Assignment 1
  def generateScorecards: Map[String, AnyRef] = ???
  def getScorecardsByName(name: String): List[ScoreCard] = ???

  //Collection Based - Assignment 2
  def getScoreCardByGender: (List[ScoreCard], List[ScoreCard]) = ???
  def getScoreCardsWithHigherPercentage: (List[ScoreCard], List[ScoreCard]) = ??? //Internally calls getScoreCardByGender
  def getSimilarPercentageBwGroups: List[((String, ScoreCard), (String, ScoreCard))] = ???
  def femalePercentageNotInMales: List[(String, ScoreCard)] = ???

}
