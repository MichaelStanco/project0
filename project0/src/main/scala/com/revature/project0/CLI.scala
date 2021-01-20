package com.revature.project0
import scala.io.StdIn
import scala.util.matching.Regex

object cli{
    def welcome(): Unit = {
        println("\nWelcome to the 2020 MLB Team Statistics App!")
    }

    def options(): Unit = {
        println("\n*** MAIN MENU ***")
        println("Enter a number to continue:")
        println("1) View Team Abbreviation Glossary")
        println("2) View 2020 MLB Team Statistics")
        println("3) Generate Analysis on 2020 MLB Teams")
        println("4) Run Game Simulation")
        println("5) View/Edit My Teams")
        println("6) Admin Tools")
        println("7) Exit application\n")
        
    }

    def suboptionsmyteams(): Unit ={
        println("\n*** MY TEAMS MENU ***")
        println("Enter a number to continue:")
        println("1) View my teams")
        println("2) Add teams to my database")
        println("3) Delete teams from my database")
        println("4) Clear all teams from my database")
        println("5) Return to main menu\n")
    }

    def analysisoptions(): Unit={
        println("\n*** ANALYSIS MENU ***")
        println("Enter a number to perform operation:")
        println("1) Generate team luck rating on 2020 MLB teams")
        println("2) Generate pitching ranks on 2020 MLB teams")
        println("3) Generate batting ranks on 2020 MLB teams")
        println("4) Return to main menu\n")
    }

    def statoptions(): Unit={
        println("\n*** 2020 MLB STATISTICS MENU ***")
        println("Enter a number to continue:")
        println("1) View 2020 MLB final standings")
        println("2) View 2020 MLB pitching statistics")
        println("3) View 2020 MLB batting statistics")
        println("4) Return to main menu\n")
    }

    def menu(): Unit = {
        welcome()

        var menuLoop = true
        while (menuLoop == true){
            options()
            var input = StdIn.readLine()
            if (input == "1"){
                actions.showallteams()
            }
            else if (input == "2"){
                var menuLoop2 = true 
                while(menuLoop2 == true){
                    statoptions()
                    var input2 = StdIn.readLine()
                    if (input2 == "1"){
                        println("\nView 2020 MLB final standings for:\n1) My Teams\n2) All teams")
                        var input21 = StdIn.readLine()
                        if (input21 == "1"){
                            println("\n2020 MLB Final Standings for My Teams:")
                            actions.showstandingsmyteams()
                        }
                        else if (input21 == "2"){
                            println("\n2020 MLB Final Standings for All Teams:")
                            actions.showstandings()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input2 == "2"){
                        println("\nView 2020 MLB pitching statistics for:\n1) My Teams\n2) All teams")
                        var input22 = StdIn.readLine()
                        if (input22 == "1"){
                            println("\n2020 MLB Pitching Statistics for My Teams:")
                            actions.showpitchingmyteams()
                        }
                        else if (input22 == "2"){
                            println("\n2020 MLB Pitching Statistics for All Teams:")
                            actions.showpitching()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input2 == "3"){
                        println("\nView 2020 MLB batting statistics for:\n1) My Teams\n2) All teams")
                        var input23 = StdIn.readLine()
                        if (input23 == "1"){
                            println("\n2020 MLB Batting Statistics for My Teams:")
                            actions.showbattingmyteams()
                        }
                        else if (input23 == "2"){
                            println("\n2020 MLB Batting Statistics for All Teams:")
                            actions.showbatting()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input2 == "4"){
                        println("\nReturning to main menu.")
                        menuLoop2 = false
                    }
                    else {
                        println("\nInvalid input - returning to main menu.\n")
                        menuLoop2 = false
                    }
                }
            }
            
            else if (input == "3"){
                var menuLoop3 = true 
                while(menuLoop3 == true){
                    analysisoptions()
                    var input3 = StdIn.readLine()
                    if (input3 == "1"){
                        println("\nView 2020 MLB luck ranking indexes for:\n1) My Teams\n2) All teams")
                        var input31 = StdIn.readLine()
                        if (input31 == "1"){
                            println("\nNOTE: The higher the luck index, the luckier the respective team was in 2020")
                            println("2020 MLB Luck Ranking Indexes for My Teams:")
                            actions.showluckmyteams()
                        }
                        else if (input31 == "2"){
                            println("\nNOTE: The higher the luck index, the luckier the respective team was in 2020")
                            println("2020 MLB Luck Ranking Indexes for All Teams:")
                            actions.showluck()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input3 == "2"){
                        println("\nView 2020 MLB pitching ranking indexes for:\n1) My Teams\n2) All teams")
                        var input32 = StdIn.readLine()
                        if (input32 == "1"){
                            println("\nNOTE: A higher pitching ranking index is indicative of increased pitching output in 2020")
                            println("2020 MLB Pitching Ranking Indexes for My Teams:")
                            actions.pitchingPImyteams()
                        }
                        else if (input32 == "2"){
                            println("\nNOTE: A higher pitching ranking index is indicative of increased pitching output in 2020")
                            println("2020 MLB Pitching Ranking Indexes for All Teams:")
                            actions.pitchingPI()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input3 == "3"){
                        println("\nView 2020 MLB batting ranking indexes for:\n1) My Teams\n2) All teams")
                        var input33 = StdIn.readLine()
                        if (input33 == "1"){
                            println("\nNOTE: A higher batting ranking index is indicative of increased batting output in 2020")
                            println("2020 MLB Batting Ranking Indexes for My Teams:")
                            actions.battingPImyteams()
                        }
                        else if (input33 == "2"){
                            println("\nNOTE: A higher batting ranking index is indicative of increased batting output in 2020")
                            println("\n2020 MLB Batting Ranking Indexes for All Teams:")
                            actions.battingPI()
                        }
                        else {
                            println("\nInvalid input.\n")
                        }
                    }
                    else if (input3 == "4"){
                        println("\nReturning to main menu.")
                        menuLoop3 = false
                    }
                    else {
                        println("\nInvalid input - returning to main menu.\n")
                        menuLoop3 = false
                    }
                }
            }
            else if (input == "5"){
                var menuLoop4 = true
                while(menuLoop4 == true){
                    suboptionsmyteams()
                    var input4 = StdIn.readLine()
                    if (input4 == "1"){
                        actions.viewmyteams()
                    }
                    else if (input4 == "2"){
                        actions.addteam()
                    }
                    else if (input4 == "3"){
                        actions.deleteteam()
                    }
                    else if (input4 == "4"){
                        actions.deleteall()
                    }
                    else if (input4 == "5"){
                        println("\nReturning to main menu.")
                        menuLoop4 = false
                    }
                    else {
                        println("\nInvalid input - returning to main menu.\n")
                        menuLoop4 = false
                    }
                }
            }
            else if(input == "4"){
                actions.runsimulation()
            }
            else if (input=="7"){
                println("\nThank you for using the 2020 MLB Team Statistics App!\n")
                menuLoop = false
            }
            else if (input == "6"){
                println("\nPlease enter password to continue:")    
                var password= StdIn.readLine()
                if (password == "password"){
                    println("\nPassword entered correctly.")
                    println("Would you like to re-populate and refresh entire app DB?\n1) Yes\n2) No\n")
                    var input5 = StdIn.readLine()
                    if (input5 == "1"){
                        Populator.populatedb()
                        ("\nDatabase has been re-populated and refreshed.\nReturning to main menu.")
                    }
                    else {
                        println("\nReturning to main menu.")
                    }
                }
                else{
                    println("\nIncorrect password entered. Returning to main menu.")
                }
            }
            else{
                println("\nInvalid input.\n")
            }
        }
    }
}