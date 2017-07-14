package com.knoldus.kip

import com.knoldus.kip.models.ScoreCard

import scala.collection.mutable.ListBuffer

object RamDatabase {
  val scoreCards : ListBuffer[ScoreCard] = new ListBuffer[ScoreCard]

  def add(scoreCard: ScoreCard) : ListBuffer[ScoreCard] = {
    scoreCards += scoreCard
  }

  def getScoreCard(studentId: Long): Option[ScoreCard] = {
    scoreCards.find(_.studentId == studentId)
  }

  def updateScoreCard(scoreCard: ScoreCard): ListBuffer[ScoreCard] = {
    getScoreCard(scoreCard.studentId) match {
      case Some(card: ScoreCard) => scoreCards -= card
        scoreCards += scoreCard
      case None => add(scoreCard)
    }
  }
  
}
