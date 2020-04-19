package com.jetacademy.simplesearchengine


fun main() {
    println("Enter the number of people:")
    val numberOfPeople = readLine()!!.toInt()
    val peopleList: MutableList<List<String>> = mutableListOf()
    println("Enter all people:")
    repeat(numberOfPeople) {
        peopleList.add(readLine()!!.split(" "))
    }
    println("Enter the number of search queries:")
    val numberOfQueries = readLine()!!.toInt()
    repeat(numberOfQueries) {
        println("Enter data to search people:")
        val searchData = readLine()!!
        val queryResult = queryPerson(peopleList, searchData)
        printQueryResult(queryResult)
    }
}

private fun printQueryResult(queryResult: List<List<String>>) {
    if (queryResult.isNotEmpty()) {
        println("Found people:")
        queryResult.map {
            println("${it.getOrElse(0) { "" }} " +
                    "${it.getOrElse(1) { "" }} " +
                    "${it.getOrElse(2) { "" }}")
        }
    } else
        println("No matching people found.")
}

private fun queryPerson(peopleList: MutableList<List<String>>, queryKeyWord: String): List<List<String>> =
    peopleList.filter { person ->
        person.any { element ->
            element.contains(queryKeyWord, ignoreCase = true)
        }
    }

