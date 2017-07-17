package com.knoldus.kip.service

import com.knoldus.kip.RamDatabase
import com.knoldus.kip.models.{Marks, Student}

trait ListAssignment {

  //Assignment -1
  def failedStudents(subjectId: Long, percentage: Double, passOrFail: String): Int = {
    passOrFail match{
      case "pass" => RamDatabase.marksList.count(mark=>(mark.subjectId == subjectId) && (mark.marksObtained>percentage))
      case "fail" => RamDatabase.marksList.count(mark=>(mark.subjectId ==subjectId ) && (mark.marksObtained<percentage))
    }
  }

  def topBottomStudents(subjectId: Long, count: Int, topOrBottom: String): List[Student] = {
    topOrBottom match{
      case "top" => RamDatabase.marksList.filter(_.subjectId == subjectId).sortWith(_.marksObtained>_.marksObtained)
                    .map((x)=>RamDatabase.getStudentById(x.studentId)).take(count)
      case "bottom" =>RamDatabase.marksList.filter(_.subjectId == subjectId).sortWith(_.marksObtained<_.marksObtained)
                    .map((x)=>RamDatabase.getStudentById(x.studentId)).take(count)
    }
  }

  def topAndLeastScorers(topOrBottom: String, count: Int): List[Student] = {
    val markMap: Map[Long,List[Marks]] = RamDatabase.marksList.groupBy(_.studentId)
    val mapWithTotals: Map[Long,Float] = markMap.mapValues(_.foldLeft(0.toFloat)((a,b)=> a + b.marksObtained))
    topOrBottom match{
      case "top" => mapWithTotals.toList.sortWith((a,b)=>a._2 > b._2).map((x)=>RamDatabase.getStudentById(x._1)).take(count)
      case "bottom" => mapWithTotals.toList.sortWith((a,b)=>a._2 <  b._2).map((x)=>RamDatabase.getStudentById(x._1)).take(count)
    }
  }

  def getScholarshipGroups(percentage: Float, goodScholarship: Int, normalScholarship: Int)
  : (List[(Student, Int)], List[(Student, Int)]) = {
    def scholarshipOrNot(actualPercentage:Float) = if(actualPercentage>percentage) goodScholarship else normalScholarship
    val markMap: Map[Long,List[Marks]] = RamDatabase.marksList.groupBy(_.studentId)
    val mapWithPercentage = markMap.mapValues((x)=>(x.foldLeft(0.toFloat)((a,b)=> a + b.marksObtained),x.size)).mapValues((a)=>a._1/a._2)
    mapWithPercentage.toList.sortBy((x)=>x._2).map((x) => (RamDatabase.getStudentById(x._1) , scholarshipOrNot(x._2))).partition(_._2 == goodScholarship)
  }

  def passedOrFailed(passOrFail: String, percentage: Float): List[Student] = {
    val markMap: Map[Long,List[Marks]] = RamDatabase.marksList.groupBy(_.studentId)
    val mapWithPercentage = markMap.mapValues((x)=>(x.foldLeft(0.toFloat)((a,b)=> a + b.marksObtained),x.size)).mapValues((a)=>a._1/a._2)
    passOrFail match {
      case "pass" => mapWithPercentage.toList.filter ((x) => x._2 > percentage).map ((x) =>RamDatabase.getStudentById (x._1))
      case "fail" => mapWithPercentage.toList.filter ((x) => x._2 < percentage).map ((x) =>RamDatabase.getStudentById (x._1))
    }
  }
  def studentsWithMoreThan95: List[Student] = {
    val markMap: Map[Long,List[Marks]] = RamDatabase.marksList.groupBy(_.studentId)
    val mapWithPercentage = markMap.mapValues((x)=>(x.foldLeft(0.toFloat)((a,b)=> a + b.marksObtained),x.size)).mapValues((a)=>a._1/a._2)
    mapWithPercentage.toList.filter(_._2>95).map((x)=>RamDatabase.getStudentById(x._1))
  }

  def generateReport: List[(String, List[Int])] = {
    val markMap = RamDatabase.marksList.groupBy(_.studentId).mapValues((x)=>x.map(_.marksObtained.toInt))
    markMap.toList.map((x)=>(RamDatabase.getStudentById(x._1).name,x._2))
  }


  //Must use the groupBy() Method of the List

  //Assignment - 2
  def getLastElementWithIndex(list: List[String]): (String,Int) = {
    def find(list: List[String], acc: Int) : (String,Int) = {
      list match {
        case head :: Nil => (head, acc)
        case _ :: tail => find(tail,acc + 1)
      }
    }
    find(list,0)
  }

  def printTable(list: List[Long]): List[Long] = {
    val multiplier = List.range(1,10)
    list.flatMap(x=> multiplier.map(_*x))
  }

  def aggregateLists(list1: List[String], list2: List[Long]): List[List[(String, Long)]] = {
    val ziplist = list1.zip(list2)
    ziplist.map(List(_))
  }

  def getSumOfList(list: List[Long]): Long = {
    def getSum(acc:Long,list:List[Long]) : Long = {
      list match {
        case h :: t => getSum(acc + h,t)
        case _ => acc
      }
    }
    getSum(0,list)
  }
  def getMultiplicationOfList(list: List[Long]) : Long = {
    def getProduct(acc:Long,list:List[Long]) : Long = {
      list match {
        case h :: t => getProduct(acc * h,t)
        case _ => acc
      }
    }
    getProduct(1,list)
  }


  def quickSortList(list : List[Long]) : List[Long] = {
    list match {
      case x :: Nil => list
      case x :: ys => {
        val pivot = x
        quickSortList (ys filter (pivot > _)) ::: List(pivot) ::: quickSortList (ys filter (pivot < _))
      }
      case Nil =>  Nil
    }
  }


  def mergeSortList(list: List[Long]): List[Long] = {
    def merge(left: List[Long], right: List[Long]): List[Long] =
      (left, right) match {
        case(left, Nil) => left
        case(Nil, right) => right
        case(leftHead :: leftTail, rightHead :: rightTail) =>
          if (leftHead < rightHead) leftHead::merge(leftTail, right) else rightHead :: merge(left, rightTail)
      }

    def mergeSort(list: List[Long]): List[Long] = {
      val n = list.length / 2
      if (n == 0)
      { list }
      else {
        val (left, right) = list.splitAt(n)
        merge(mergeSort(left), mergeSort(right))
      }
    }
    mergeSort(list)
  }


}
