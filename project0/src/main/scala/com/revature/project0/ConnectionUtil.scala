package com.revature.project0

import java.sql.Connection
import java.sql.DriverManager

object ConnectionUtil {

  var conn: Connection = null;

  def getConnection(): Connection = {

    if (conn == null || conn.isClosed()) {
      classOf[org.postgresql.Driver].newInstance()
      conn = DriverManager.getConnection(
        "jdbc:postgresql://scala-spark-usf-db.c32anyjmqflo.us-east-1.rds.amazonaws.com:5432/nyystanco?currentSchema=project0",
        "nyystanco", 
        "wasspord284"
      )
    }
    conn
  }

}