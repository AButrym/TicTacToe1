package com.softserve.academy.tictactoe.demo.delegation

class LateInitDemo {
    lateinit var name: String

    fun getNameSafe(): String =
        if (::name.isInitialized) name else "N/A"
}

fun main() {
    val o = LateInitDemo()
    o.name = "Name"
    println(o.getNameSafe())
}