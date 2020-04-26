package search

import java.io.File

fun main(args: Array<String>) {
    val fileName = parseComandLineArgs(args)
    val (dataSet, inverseIndex) = loadDataFromFile(fileName)
    var menuComand = -1
    while (menuComand != 0) {
        printMenu()
        menuComand = readLine()!!.toInt()
        when (menuComand) {
            1 -> findAPerson(dataSet, inverseIndex)
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

private fun loadDataFromFile(fileName: String): Pair<List<String>, Map<String, MutableList<Int>>> {
    val file = File(fileName)
    val peopleList: MutableList<String> = mutableListOf()
    val inverseIndex: MutableMap<String, MutableList<Int>> = mutableMapOf()
    var lineIndex = 0
    file.forEachLine { personInfo ->
        peopleList.add(personInfo)
        personInfo.toLowerCase().split(" ").map { personElement ->
            if (inverseIndex.containsKey(personElement))
                inverseIndex[personElement]!!.add(lineIndex)
            else
                inverseIndex[personElement] = mutableListOf(lineIndex)
        }
        lineIndex++
    }
    return Pair(peopleList.toList(), inverseIndex.toMap())
}


private fun printMenu() =
    println("=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit")

private fun exit() = println("Bye!")

private fun findAPerson(peopleList: List<String>, inverseIndex: Map<String, MutableList<Int>>) {
    println("Enter a name or email to search all suitable people.")
    val searchData = readLine()!!
    val queryResult = queryPerson(peopleList, searchData, inverseIndex)
    printQueryResult(queryResult)
}

private fun printAllPeople(peopleList: List<String>) {
    println("=== List of people ===")
    printQueryResult(peopleList)
}

private fun printQueryResult(queryResult: List<String>) {
    if (queryResult.isNotEmpty()) {
        println("${queryResult.size} persons found:")
        queryResult.map(::println)
    } else
        println("No matching people found.")
}

private fun queryPerson(peopleList: List<String>, queryKeyWord: String, inverseIndex: Map<String, MutableList<Int>>): List<String> {
    val normalizedKeyWord = queryKeyWord.toLowerCase()
    if (inverseIndex.containsKey(normalizedKeyWord))
        return inverseIndex[normalizedKeyWord]!!.map { peopleList[it] }
    else
        return listOf()
}