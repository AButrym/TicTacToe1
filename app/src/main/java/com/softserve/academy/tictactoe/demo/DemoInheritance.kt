package com.softserve.academy.tictactoe.demo

//open class Person1(val name: String, val id: Int)
open class Person1(val name: String) {
    val id: Int = ++count

    companion object {
        private var count = 0
    }
}

class Student1(
    name: String, var group: String
) : Person1(name) {
    override fun toString(): String {
        return "Student1(id=$id, name='$name', group='$group')"
    }
}

class Teacher1(name: String, var faculty: String
) : Person1(name) {
    override fun toString(): String {
        return "Teacher1(id=$id, name='$name', faculty='$faculty')"
    }
}

fun main() {
    val person1: Person1 = Student1("Alice", "Group11")
    val person2: Person1 = Teacher1("Alice", "Faculty1")
    val person22: Teacher1 = Teacher1("Alice", "Faculty1")
    var person3: Person1 = person1
    person3 = person2
    person3 = person22
    println(person22)
}