package com.knoldus.kip.service

import com.knoldus.kip.RamDatabase
import com.knoldus.kip.models.{ScoreCard, Student,Gender}

trait CollectionAssignment {

  //Collection Based - Assignment 1

  protected def getStudentMarks(id:Long) : Map[Long,Float]  = {
    RamDatabase.marksList.filter(_.studentId == id).groupBy(_.subjectId).map{case(k,v)=>(k.toLong,v.head.marksObtained)}
  }

  protected def getPercentage(id:Long) : Float = {
    val listOfMarks = RamDatabase.marksList.filter(_.studentId == id)
    listOfMarks.foldLeft(0.toFloat)((x,y)=> x + y.marksObtained)/listOfMarks.size
    //The power of fold!
  }
  def generateScorecards: Map[String, AnyRef] = {

    val nametoScorecardList = RamDatabase.studentList
        .map((student)=>(student.name,ScoreCard(student.id,getStudentMarks(student.id),getPercentage(student.id))))
        .groupBy(_._1).mapValues((a)=> a.map((x)=>x._2))
       nametoScorecardList.mapValues((x)=> if(x.size==1) x.head else x)
  }

  def getScorecardsByName(name: String): List[ScoreCard] = {
    val scoreCard = generateScorecards.getOrElse(name,"na")
    scoreCard match{
      case List(_*) => scoreCard.asInstanceOf[List[ScoreCard]]
      case x:ScoreCard => List(x.asInstanceOf[ScoreCard])
      case _ => throw new Exception("Name not found")
    }
  }

  //Collection Based - Assignment 2
  def getScoreCardByGender: (List[ScoreCard], List[ScoreCard]) = {
    val (male,female) = RamDatabase.studentList.partition(_.gender == Gender.MALE)
    (male.map((student)=> ScoreCard(student.id,getStudentMarks(student.id),getPercentage(student.id))),
      female.map((student)=> ScoreCard(student.id,getStudentMarks(student.id),getPercentage(student.id))))

  }
  def getScoreCardsWithHigherPercentage: (List[ScoreCard], List[ScoreCard]) = {
    val(male,female) = getScoreCardByGender
    (male.filter(_.percentage>50),female.filter(_.percentage>50))
  } //Internally calls getScoreCardByGender

  def getSimilarPercentageBwGroups: List[((String, ScoreCard), (String, ScoreCard))] = {
    val(male,female) = getScoreCardByGender
    val marksIntersect = male.map((x)=>x.percentage).intersect(female.map((x)=>x.percentage))

    male.sortBy((scorecard)=>scorecard.percentage).map((x)=>(RamDatabase.getStudentById(x.studentId).name,x)).filter((x)=> female.contains(x._2.percentage))zip
      female.sortBy((scorecard)=>scorecard.percentage).map((x)=>(RamDatabase.getStudentById(x.studentId).name,x)).filter((x)=> marksIntersect.contains(x._2.percentage))
  }

  def femalePercentageNotInMales: List[(String, ScoreCard)] = {
    val(male,female) = getScoreCardByGender
    val marksDiff = female.map((x)=>x.percentage).diff(male.map((x)=>x.percentage))
    female.map((x)=>(RamDatabase.getStudentById(x.studentId).name,x)).filter((x)=> marksDiff.contains(x._2.percentage))
  }

}
