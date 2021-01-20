package com.revature.project0

object CSV{
        //read in Standings
        val nrows1 = 31
        val ncols1 = 13
        val rowsStandings = Array.ofDim[String](nrows1, ncols1)
        val bufferedSourceStandings = io.Source.fromFile("/home/nyystanco6/projects/project0/project0/csvs/standings.csv")
        var count1 = 0
        for (line <- bufferedSourceStandings.getLines) {
            rowsStandings(count1) = line.split(",").map(_.trim)
            count1 += 1
        }
        bufferedSourceStandings.close

        //read in pitching
        val nrows2 = 31
        val ncols2 = 20
        val rowsPitching = Array.ofDim[String](nrows2, ncols2)
        val bufferedSourcePitching = io.Source.fromFile("/home/nyystanco6/projects/project0/project0/csvs/pitching.csv")
        var count2 = 0
        for (line <- bufferedSourcePitching.getLines) {
            rowsPitching(count2) = line.split(",").map(_.trim)
            count2 += 1
        }
        bufferedSourcePitching.close
        for (i <- 0 until nrows2) {
            //println(rowsPitching(i)(2))
        }
        
        //read in Batting
        val nrows3 = 31
        val ncols3 = 23
        val rowsBatting = Array.ofDim[String](nrows3, ncols3)
        val bufferedSourceBatting = io.Source.fromFile("/home/nyystanco6/projects/project0/project0/csvs/batting.csv")
        var count3 = 0
        for (line <- bufferedSourceBatting.getLines) {
            rowsBatting(count3) = line.split(",").map(_.trim)
            count3 += 1
        }
        bufferedSourceBatting.close
        for (i <- 0 until nrows3) {
            //println(rowsBatting(i)(0))
        }
}



