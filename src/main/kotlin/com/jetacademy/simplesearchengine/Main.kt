package com.jetacademy.simplesearchengine

import java.io.File
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    val fileName = parseComandLineArgs(args)
    val dataSet: DataSet = loadDataFromFile(fileName)
    var menuCommand = -1
    while (menuCommand != 0) {
        printMenu()
        menuCommand = readLine()!!.toInt()
        when (menuCommand) {
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

private fun loadDataFromFile(fileName: String): DataSet {
    val file = File(fileName)
    val peopleList: MutableList<Person> = mutableListOf()
    val invertedIndex: MutableMap<String, MutableSet<Int>> = mutableMapOf()
    var lineIndex = 0
    file.forEachLine { lineInfo ->
        val personInfo =  lineInfo.split(" ")
        peopleList.add(
            Person(
                firstName = personInfo[0],
                lastName = personInfo.getOrNull(1),
                email = personInfo.getOrNull(2)
            )
        )
        lineInfo.toLowerCase().split(" ").map { personElement ->
            if (invertedIndex.containsKey(personElement))
                invertedIndex[personElement]!!.add(lineIndex)
            else
                invertedIndex[personElement] = mutableSetOf(lineIndex)
        }
        lineIndex++
    }
    return DataSet(
        dataList = peopleList.toList(),
        invertedIndex = invertedIndex.toMap()
    )
}


private fun printMenu() =
    println("=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all persons\n" +
            "0. Exit")

private fun exit() = println("Bye!")

private fun findAPerson(dataSet: DataSet) {
    try {
        println("Select a matching strategy: ${SearchStrategyType.values().joinToString(separator = ", ") { it.name }}")
        val searchStrategy = SearchStrategyType.valueOf(readLine()!!)
        println("Enter a name or email to search all suitable people.")
        val queryKeyWords = readLine()!!.split(" ")
        val queryResult = searchStrategy.search(dataSet, queryKeyWords)
        printDataList(queryResult)
    } catch (e: IllegalArgumentException) {
        println("There is no such search engine!")
    }
}

private fun printAllPeople(dataSet: DataSet) {
    println("=== List of people ===")
    printDataList(dataSet.dataList)
}

private fun printDataList(dataList: List<FileData>) {
    if (dataList.isNotEmpty()) {
        println("${dataList.size} persons found:")
        dataList.map(::println)
    } else
        println("No matching people found.")
}


