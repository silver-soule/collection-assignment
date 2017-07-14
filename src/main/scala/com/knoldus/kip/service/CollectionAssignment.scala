package com.knoldus.kip.service

import com.knoldus.kip.models.ScoreCard

trait CollectionAssignment {

  //Collection Based - Assignment 1
  def generateScorecards: Map[String, AnyRef] = ???
  def getScorecardsByName(name: String): List[ScoreCard] = ???

  //Collection Based - Assignment 2
  def getScoreCardByGender: (List[ScoreCard], List[ScoreCard]) = ???
  def getScoreCardsWithHigherPercentage: (List[ScoreCard], List[ScoreCard]) = ??? //Internally calls getScoreCardByGender
  def getSimilarPercentageBwGroups: List[((String, ScoreCard), (String, ScoreCard))] = ???
  def femalePercentageNotInMales: List[(String, ScoreCard)] = ???

}
