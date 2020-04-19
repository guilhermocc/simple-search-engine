package com.jetacademy.simplesearchengine

import java.io.File

fun main(args: Array<String>) {
    val fileName = parseComandLineArgs(args)
    val dataSet = loadDataFromFile(fileName)
    var menuComand = -1
    while (menuComand != 0) {
        printMenu()
        menuComand = readLine()!!.toInt()
        when (menuComand) {
            1 -> findAPerson(dataSet)
            2 -> printAllPeople(dataSet)
            0 -> exit()
            else -> println("Incorrect option! Try again.")
        }
    }
}

fun parseComandLineArgs(args: Array<String>): String {
    val fileNameIndex = args.indexOf("--data") + 1
    return args[fileNameIndex]
}

private fun loadDataFromFile(fileName: String): List<String> {
    val file = File(fileName)
    val peopleList: MutableList<String> = mutableListOf()
    file.forEachLine {
        peopleList.add(it)
    }
    return peopleList.toList()
}


private fun printMenu() =
    println(
        "=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit"
    )

private fun exit() = println("Bye!")

private fun findAPerson(peopleList: List<String>) {
    println("Enter data to search people:")
    val searchData = readLine()!!
    val queryResult = queryPerson(peopleList, searchData)
    printQueryResult(queryResult)
}

private fun printAllPeople(peopleList: List<String>) {
    println("=== List of people ===")
    printQueryResult(peopleList)
}

private fun printQueryResult(queryResult: List<String>) {
    if (queryResult.isNotEmpty()) {
        queryResult.map(::println)
    } else
        println("No matching people found.")
}

private fun queryPerson(peopleList: List<String>, queryKeyWord: String): List<String> =
    peopleList.filter { person ->
        person.contains(queryKeyWord, ignoreCase = true)
    }

