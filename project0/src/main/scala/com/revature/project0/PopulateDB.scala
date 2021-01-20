package com.revature.project0

object Populator{
    val conn = ConnectionUtil.getConnection();
    def populatedb() = {    
        //populate standings table
        val clearStandings = conn.prepareStatement("DELETE FROM standings;")
        clearStandings.execute()
        for (i <- 1 until CSV.nrows1) {
            val insertStandings = conn.prepareStatement(s"INSERT INTO standings(Tm, Lg, G, W, L, WL, R, RA, Rdiff, SOS, SRS, pythWL, luck) values('${CSV.rowsStandings(i)(0)}', '${CSV.rowsStandings(i)(1)}', ${CSV.rowsStandings(i)(2)}, ${CSV.rowsStandings(i)(3)}, ${CSV.rowsStandings(i)(4)}, ${CSV.rowsStandings(i)(5)}, ${CSV.rowsStandings(i)(6)}, ${CSV.rowsStandings(i)(7)}, ${CSV.rowsStandings(i)(8)}, ${CSV.rowsStandings(i)(9)}, ${CSV.rowsStandings(i)(10)}, '${CSV.rowsStandings(i)(11)}', ${CSV.rowsStandings(i)(12)});")
            insertStandings.execute()
        }
        //populate pitching table
        val clearPitching = conn.prepareStatement("DELETE FROM pitching;")
        clearPitching.execute()
        for (i <- 1 until CSV.nrows2) {
            val insertPitching = conn.prepareStatement(s"INSERT INTO pitching(Tm, numP, PAge, RAperG, W,L,WL,ERA,G,GS,SV,IP,H,R,ER,HR,BB,SO,HBP,WHIP) values('${CSV.rowsPitching(i)(0)}',${CSV.rowsPitching(i)(1)},${CSV.rowsPitching(i)(2)},${CSV.rowsPitching(i)(3)},${CSV.rowsPitching(i)(4)},${CSV.rowsPitching(i)(5)},${CSV.rowsPitching(i)(6)},${CSV.rowsPitching(i)(7)},${CSV.rowsPitching(i)(8)},${CSV.rowsPitching(i)(9)},${CSV.rowsPitching(i)(10)},${CSV.rowsPitching(i)(11)},${CSV.rowsPitching(i)(12)},${CSV.rowsPitching(i)(13)},${CSV.rowsPitching(i)(14)},${CSV.rowsPitching(i)(15)},${CSV.rowsPitching(i)(16)},${CSV.rowsPitching(i)(17)},${CSV.rowsPitching(i)(18)},${CSV.rowsPitching(i)(19)});")
            insertPitching.execute()
        }
        //populate batting table
        val clearBatting = conn.prepareStatement("DELETE FROM batting;")
        clearBatting.execute()
        for (i <- 1 until CSV.nrows3) {
            val insertBatting = conn.prepareStatement(s"INSERT INTO batting(Tm,numBat,BatAge,RperG,G,PA,AB,R,H,doubles,triples,HR,RBI,SB,CS,BB,SO,BA,OBP,SLG,OPS,TB,LOB) values('${CSV.rowsBatting(i)(0)}',${CSV.rowsBatting(i)(1)},${CSV.rowsBatting(i)(2)},${CSV.rowsBatting(i)(3)},${CSV.rowsBatting(i)(4)},${CSV.rowsBatting(i)(5)},${CSV.rowsBatting(i)(6)},${CSV.rowsBatting(i)(7)},${CSV.rowsBatting(i)(8)},${CSV.rowsBatting(i)(9)},${CSV.rowsBatting(i)(10)},${CSV.rowsBatting(i)(11)},${CSV.rowsBatting(i)(12)},${CSV.rowsBatting(i)(13)},${CSV.rowsBatting(i)(14)},${CSV.rowsBatting(i)(15)},${CSV.rowsBatting(i)(16)},${CSV.rowsBatting(i)(17)},${CSV.rowsBatting(i)(18)},${CSV.rowsBatting(i)(19)},${CSV.rowsBatting(i)(20)},${CSV.rowsBatting(i)(21)},${CSV.rowsBatting(i)(22)});")
            insertBatting.execute()
        }
        //clear myteams
        val clearmyteams = conn.prepareStatement("DELETE FROM myteams;")
        clearmyteams.execute()
    }
}