package com.revature.project0
import scala.io.StdIn
import scala.util.matching.Regex
import scala.util.Using
import scala.util.Random
import java.sql.ResultSet
import java.sql.ResultSetMetaData


object actions{
    val conn = ConnectionUtil.getConnection();

    def viewmyteams(): Array[String] = {
        val viewteams = conn.prepareStatement("select * from myteams;")
        viewteams.execute()
        val viewresults = viewteams.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        println("\nYour current teams are:")
        while(viewresults.next()){
            myteams(arraycount) = viewresults.getString("tm")
            println(myteams(arraycount))
            arraycount = arraycount + 1
        }
        println(s"Current number of teams in database: ${arraycount}")
        return myteams
    }
    //viewmyteams()

    def addteam(): Unit = {
        var newteam : String = ""
        println("\nPlease enter 3-character abbreviation for team to add:")
        newteam = scala.io.StdIn.readLine().toUpperCase()
        val myteams = viewmyteams()
        if(myteams contains newteam){
            println("\nThe specified team is already contained in your database.\n")
        }
        else if(newteam.matches("ARI|ATL|BAL|BOS|CHC|CHW|CIN|CLE|COL|DET|HOU|KCR|LAA|LAD|MIA|MIL|MIN|NYM|NYY|OAK|PHI|PIT|SDP|SEA|SFG|STL|TBR|TEX|TOR|WSN")){
            val addteams = conn.prepareStatement(s"insert into myteams(Tm) values ('${newteam}')")
            addteams.execute()
            val myteamsupdated = viewmyteams()
            println("\nTeam successfully added to your database. Your current teams are:")
            var i = 0
            while (myteamsupdated(i) != null) {
                println(myteamsupdated(i))
                i += 1
            }   
        }
        else{
            println("\nInvalid team abbreviation entered.\n")
        }
    }
    //addteam()

    def deleteteam(): Unit = {
        var deleteteam : String = ""
        println("\nPlease enter 3-character abbreviation for team to delete:")
        deleteteam = scala.io.StdIn.readLine().toUpperCase()
        val myteams = viewmyteams()
        if(myteams contains deleteteam){
            val deleteteams = conn.prepareStatement(s"DELETE FROM myteams WHERE Tm = '${deleteteam}'")
            deleteteams.execute()
            val myteamsupdated = viewmyteams()
            println("\nTeam successfully deleted from your database. Your current teams are:")
            var i = 0
            while (myteamsupdated(i) != null) {
                println(myteamsupdated(i))
                i += 1
            }   
        }
        else{
            println("\nInvalid entry, or team is not currently contained in database.\n")
        }
    }
    //deleteteam()

    def deleteall(): Unit ={
        var deleteall: String = ""
        println("\nAre you sure you would like to remove all teams from your database? (Y/N)")
        deleteall = scala.io.StdIn.readLine().toUpperCase()
        if(deleteall == "Y"){
            println("\nAll teams were successfully removed from your database.\n")
            val deleteallteams = conn.prepareStatement(s"DELETE FROM myteams;")
            deleteallteams.execute()
        }
        else{
            println("\nNo teams were removed from your database.\n")
        }
    }
    //deleteall()

    def showallteams(): Unit ={
        println("\nValid 3-character MLB team abbreviations are as follows:\nARI - Arizona Diamondbacks\nATL - Atlanta Braves\nBAL - Baltimore Orioles\nBOS - Boston Red Sox\nCHC - Chicago Cubs\nCHW - Chicago White Sox\nCIN - Cincinnati Reds\nCLE - Cleveland Indians\nCOL - Colorado Rockies\nDET - Detroit Tigers\nHOU - Houston Astros\nKCR - Kansas City Royals\nLAA - Las Angeles Angels\nLAD - Las Angeles Dodgers\nMIA - Miami Marlins\nMIL - Milwaukee Brewers\nMIN - Minnesota Twins\nNYM - New York Mets\nNYY - New York Yankees\nOAK - Oakland Athletics\nPHI - Philadelphia Phillies\nPIT - Pittsburgh Pirates\nSDP - San Diego Padres\nSEA - Seatte Mariners\nSFG - San Francisco Giants\nSTL - St. Louis Cardinals\nTBR - Tampa Bay Rays\nTEX - Texas Rangers\nTOR - Toronto Blue Jays\nWSN - Washington Nationals\n")
    }
    //showallteams()

    def showpitching(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, w as Wins, l as Losses, era as ERA, whip as WHIP, sv as Saves, ip as InningsPitched, hr as HomeRunsAllowed, bb as WalksAllowed, so as StrikeOuts from pitching order by era;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team Win Losses Era WHIP Saves InningsPitched HomeRunsAllowed WalksAllowed StrikeOuts")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("Wins") + (" ") + resultset.getString("Losses") +  (" ") + resultset.getString("ERA") + (" ") + resultset.getString("WHIP") + (" ") + resultset.getString("Saves") + (" ") + resultset.getString("InningsPitched") + (" ") + resultset.getString("HomeRunsAllowed")+ (" ") + resultset.getString("WalksAllowed") + (" ") + resultset.getString("StrikeOuts")     
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }
    //showpitching()

    def showpitchingmyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, w as Wins, l as Losses, era as ERA, whip as WHIP, sv as Saves, ip as InningsPitched, hr as HomeRunsAllowed, bb as WalksAllowed, so as StrikeOuts from pitching as a inner join myteams as b on a.tm = b.tm order by era;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team Win Losses Era WHIP Saves InningsPitched HomeRunsAllowed WalksAllowed StrikeOuts")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("Wins") + (" ") + resultset.getString("Losses") +  (" ") + resultset.getString("ERA") + (" ") + resultset.getString("WHIP") + (" ") + resultset.getString("Saves") + (" ") + resultset.getString("InningsPitched") + (" ") + resultset.getString("HomeRunsAllowed")+ (" ") + resultset.getString("WalksAllowed") + (" ") + resultset.getString("StrikeOuts")     
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def showbatting(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, r as RunsScored, h as Hits, doubles as Doubles, triples as Triples, hr as HR, rbi as RBI, ba as BattingAvg, obp as OnBasePct, slg as SluggingPCt from batting order by r desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team RunsScored Hits Doubles Triples HR RBI BattingAvg OnBasePct SluggingPct")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("RunsScored") + (" ") + resultset.getString("Hits") +  (" ") + resultset.getString("Doubles") + (" ") + resultset.getString("Triples") + (" ") + resultset.getString("HR") + (" ") + resultset.getString("RBI") + (" ") + resultset.getString("BattingAvg")+ (" ") + resultset.getString("OnBasePct") + (" ") + resultset.getString("SluggingPct")     
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def showbattingmyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, r as RunsScored, h as Hits, doubles as Doubles, triples as Triples, hr as HR, rbi as RBI, ba as BattingAvg, obp as OnBasePct, slg as SluggingPct from batting as a inner join myteams as b on a.tm = b.tm order by r desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team RunsScored Hits Doubles Triples HR RBI BattingAvg OnBasePct SluggingPct")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("RunsScored") + (" ") + resultset.getString("Hits") +  (" ") + resultset.getString("Doubles") + (" ") + resultset.getString("Triples") + (" ") + resultset.getString("HR") + (" ") + resultset.getString("RBI") + (" ") + resultset.getString("BattingAvg")+ (" ") + resultset.getString("OnBasePct") + (" ") + resultset.getString("SluggingPct")     
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def showstandings(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, lg as League, w as Wins, l as Losses, r as RunsScored, ra as RunsAllowed, rdiff as RunDifferential from standings order by w desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team Leauge Wins Losses RunsScored RunsAllowed RunDifferential")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("League") + (" ") + resultset.getString("Wins") +  (" ") + resultset.getString("Losses") + (" ") + resultset.getString("RunsScored") + (" ") + resultset.getString("RunsAllowed") + (" ") + resultset.getString("RunDifferential")
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }
    def showstandingsmyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, lg as League, w as Wins, l as Losses, r as RunsScored, ra as RunsAllowed, rdiff as RunDifferential from standings as a inner join myteams as b on a.tm = b.tm order by w desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team Leauge Wins Losses RunsScored RunsAllowed RunDifferential")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("League") + (" ") + resultset.getString("Wins") +  (" ") + resultset.getString("Losses") + (" ") + resultset.getString("RunsScored") + (" ") + resultset.getString("RunsAllowed") + (" ") + resultset.getString("RunDifferential")
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def showluck(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, luck from standings order by luck desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team LuckRating")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("luck") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def showluckmyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, luck from standings as a inner join myteams as b on a.tm = b.tm order by luck desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team LuckRating")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("luck") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def pitchingPI(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, ((10-era)*(2-whip)*(wl))as PitchingPowerRankMetric from pitching order by PitchingPowerRankMetric desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team PitchingPowerRankMetric")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("PitchingPowerRankMetric") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def pitchingPImyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, ((10-era)*(2-whip)*(wl))as PitchingPowerRankMetric from pitching as a inner join myteams as b on a.tm = b.tm order by PitchingPowerRankMetric desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team PitchingPowerRankMetric")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("PitchingPowerRankMetric") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def battingPI(): Unit ={
        val statement = conn.prepareStatement("select tm as Team, (rperg*ba*ops)as BattingPowerRankMetric from batting order by BattingPowerRankMetric desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team BattingPowerRankMetric")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("BattingPowerRankMetric") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def battingPImyteams(): Unit ={
        val statement = conn.prepareStatement("select a.tm as Team, (rperg*ba*ops)as BattingPowerRankMetric from batting as a inner join myteams as b on a.tm = b.tm order by BattingPowerRankMetric desc;")
        statement.execute()
        val resultset = statement.getResultSet()
        val myteams = new Array[String](31)
        var arraycount = 0
        
        println("Team BattingPowerRankMetric")
        while (resultset.next()){
            myteams(arraycount) = resultset.getString("Team") + (" ") + resultset.getString("BattingPowerRankMetric") 
            println(myteams(arraycount))
            arraycount = arraycount + 1

        }
    }

    def runsimulation(): Unit ={
        var team1 : String = ""
        var team2 : String = ""
        println("\nPlease enter 3-character abbreviation for team one:")
        team1 = scala.io.StdIn.readLine().toUpperCase()
        while (team1.matches("ARI|ATL|BAL|BOS|CHC|CHW|CIN|CLE|COL|DET|HOU|KCR|LAA|LAD|MIA|MIL|MIN|NYM|NYY|OAK|PHI|PIT|SDP|SEA|SFG|STL|TBR|TEX|TOR|WSN") == false){
            println("\nTeam not found. Please enter a valid team abbreviation:")
            team1 = scala.io.StdIn.readLine().toUpperCase()
        }
        println("\nPlease enter 3-character abbreviation for team two:")
        team2 = scala.io.StdIn.readLine().toUpperCase()
        while (team2.matches("ARI|ATL|BAL|BOS|CHC|CHW|CIN|CLE|COL|DET|HOU|KCR|LAA|LAD|MIA|MIL|MIN|NYM|NYY|OAK|PHI|PIT|SDP|SEA|SFG|STL|TBR|TEX|TOR|WSN") == false){
            println("\nTeam not found. Please enter a valid team abbreviation:")
            team2 = scala.io.StdIn.readLine().toUpperCase()
        }
        println("\nPlease enter number of games to be simulated:")
        var numgames = scala.io.StdIn.readInt()
        //println(numgames)
        /*if (numgames > 10){
            ("\nToo many games entered. Please enter the number of games to be simulated (up to 10):")
            var numgames = scala.io.StdIn.readInt()
        }*/
        val team1query = conn.prepareStatement(s"select * from standings where tm = '${team1}'")
        team1query.execute()
        val resultset1 = team1query.getResultSet()
        val team2query = conn.prepareStatement(s"select * from standings where tm = '${team2}'")
        team2query.execute()
        val resultset2 = team2query.getResultSet()

        val team1arr1 = new Array[Double](1)
        val team1arr2 = new Array[Double](1)
        val team2arr1 = new Array[Double](1)
        val team2arr2 = new Array[Double](1)

        var arraycount = 0
        
        //println("R RA")
        while (resultset1.next()){
            team1arr1(arraycount) = resultset1.getDouble("r")
            //println(team1arr1(arraycount))
            team1arr2(arraycount) = resultset1.getDouble("ra")
            //println(team1arr2(arraycount))
        }
        while (resultset2.next()){
            team2arr1(arraycount) = resultset2.getDouble("r")
            //println(team2arr1(arraycount))
            team2arr2(arraycount) = resultset2.getDouble("ra")
            //println(team2arr2(arraycount))
        }
        
        var team1score = 0.0
        var team2score = 0.0
        var team1wins = 0 
        var team2wins = 0
        var gamecount = 1
        var team1runs = 0
        var team2runs = 0
        var team1runsD = 0.0
        var team2runsD = 0.0

        while (gamecount <= numgames){
            var team1scoreI = 0
            var team2scoreI = 0
            team1score = ((team1arr1(arraycount)/2) + (team2arr2(arraycount)/2) + (Random.nextGaussian()*3.3))
            if (team1score < 0){
                team1score = 0
            }
            team2score = ((team2arr1(arraycount)/2) + (team1arr2(arraycount)/2) + (Random.nextGaussian()*3.3))
            if (team2score < 0){
                team2score = 0
            }

            //println(team1score)
            //println(team2score)
            if (team1score > team2score){
                team1score = team1score.ceil
                team2score = team2score.floor
                team1scoreI = team1score.toInt
                team2scoreI = team2score.toInt
                team1wins += 1
            }
            else if (team2score > team1score){
                team2score = team2score.ceil
                team1score = team1score.floor
                team1scoreI = team1score.toInt
                team2scoreI = team2score.toInt
                team2wins += 1
            }

            team1runs = team1runs + team1scoreI
            team2runs = team2runs + team2scoreI

            println(s"Game ${gamecount} results: ${team1} ${team1scoreI} - ${team2} ${team2scoreI}")
            println(s"Current win total in simulation: ${team1} ${team1wins}, ${team2} ${team2wins}")
            gamecount += 1
        }
        team1runsD = team1runs.toDouble/gamecount
        team2runsD = team2runs.toDouble/gamecount


        println(s"\nFINAL RESULTS OF SIMULATION:\n${team1} - ${team1wins} wins\n${team2} - ${team2wins} wins")
        println(s"\nTOTAL RUNS SCORED DURING SIMULATION:\n${team1} - ${team1runs} runs\n${team2} - ${team2runs} runs")
        println(s"\nRUNS SCORED PER GAME DURING SIMULATION:\n${team1} - ${team1runsD} runs scored per game\n${team2} - ${team2runsD} runs scored per game")

    }

    
}