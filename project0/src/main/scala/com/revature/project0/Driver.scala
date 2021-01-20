package com.revature.project0

import java.sql.Connection
import java.sql.DriverManager

object Driver {
  def main(args: Array[String]) : Unit = {    
    //println("hello")  
    val conn = DriverManager.getConnection("jdbc:postgresql://scala-spark-usf-db.c32anyjmqflo.us-east-1.rds.amazonaws.com:5432/nyystanco?currentSchema=project0","nyystanco", "wasspord284")

  }
  cli.menu()
}