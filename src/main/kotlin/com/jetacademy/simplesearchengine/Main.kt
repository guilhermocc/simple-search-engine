package com.jetacademy.simplesearchengine

fun main() {
    val wordsList = readLine()!!.split(" ")
    val searchKeyWord = readLine()!!
    val index = wordsList.indexOf(searchKeyWord) + 1
    print(if (index == 0) "Not found" else index)
}