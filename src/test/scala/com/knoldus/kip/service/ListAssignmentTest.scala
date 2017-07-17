package com.knoldus.kip.service

import com.knoldus.kip.RamDatabase
import com.knoldus.kip.models.Student

/**
  * Created by Neelaksh on 16/7/17.
  */
class ListAssignmentTest extends org.scalatest.FunSuite {

  val listAssignment = new{} with ListAssignment {}
  val topthreestudents = List(RamDatabase.getStudentById(1),RamDatabase.getStudentById(2),RamDatabase.getStudentById(9))
  val bottomthreestudents = List(RamDatabase.getStudentById(6),RamDatabase.getStudentById(10),RamDatabase.getStudentById(3))
  val toptwooverall = List(RamDatabase.getStudentById(1),RamDatabase.getStudentById(2))
  val goodScholarShip = List((RamDatabase.getStudentById(1),5000),(RamDatabase.getStudentById(2),5000))

  val badScholarShip:List[(Student,Int)] = List((RamDatabase.getStudentById(6),0),(RamDatabase.getStudentById(10),0),
    (RamDatabase.getStudentById(8),0),(RamDatabase.getStudentById(5),0),(RamDatabase.getStudentById(3),0),
    (RamDatabase.getStudentById(7),0),(RamDatabase.getStudentById(9),0),(RamDatabase.getStudentById(4),0))
  test("check failed students"){
    assert(listAssignment.failedStudents(1,80,"pass") == 3)
    assert(listAssignment.failedStudents(1,60,"fail") == 2)
  }

  test(" testBottomStudents"){
    assert(listAssignment.topBottomStudents(1,3,"top") == topthreestudents)
    assert(listAssignment.topBottomStudents(1,3,"bottom") == bottomthreestudents)
  }
  test("top scorer"){
    assert(listAssignment.topAndLeastScorers("top",2) == toptwooverall)
  }

  test("scholarship groups"){
    val ok =listAssignment.getScholarshipGroups(84,5000,0)
    val normal = ok._2
    val good = ok._1
    assert(good == goodScholarShip)
    assert(normal == badScholarShip)
  }


}
