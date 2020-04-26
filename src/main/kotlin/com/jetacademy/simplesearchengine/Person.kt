package com.jetacademy.simplesearchengine

data class Person (
        val firstName: String,
        val lastName: String?,
        val email: String?
): FileData {
    override fun toString(): String = listOfNotNull(firstName, lastName, email).joinToString(" ")
}