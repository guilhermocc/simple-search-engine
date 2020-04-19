package com.jetacademy.simplesearchengine

fun main() {
    println("Enter the number of people:")
    val numberOfPeople = readLine()!!.toInt()
    val peopleList: MutableList<String> = mutableListOf()
    println("Enter all people:")
    repeat(numberOfPeople) {
        peopleList.add(readLine()!!)
    }

    var menuComand = -1
    while (menuComand != 0) {
        printMenu()
        menuComand = readLine()!!.toInt()
        when (menuComand) {
            1 -> findAPerson(peopleList)
            2 -> printAllPeople(peopleList)
            0 -> exit()
            else -> println("Incorrect option! Try again.")
        }
    }
}


private fun printMenu() =
    println(
        "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit"
    )

private fun exit() = println("Bye!")

private fun findAPerson(peopleList: MutableList<String>) {
    println("Enter data to search people:")
    val searchData = readLine()!!
    val queryResult = queryPerson(peopleList, searchData)
    printQueryResult(queryResult)
}

private fun printAllPeople(peopleList: MutableList<String>) {
    println("=== List of people ===")
    printQueryResult(peopleList)
}

private fun printQueryResult(queryResult: List<String>) {
    if (queryResult.isNotEmpty()) {
        queryResult.map(::println)
    } else
        println("No matching people found.")
}

private fun queryPerson(peopleList: MutableList<String>, queryKeyWord: String): List<String> =
    peopleList.filter { person ->
        person.contains(queryKeyWord, ignoreCase = true)
    }
