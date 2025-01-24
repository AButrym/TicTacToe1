package com.softserve.academy.tictactoe.demo

fun main() {
    val o : String? = "null"
    val b = o?.length ?: getDefault()
    println(b)
    val arr = IntArray(5) { -5 }
    val arr1 = IntArray(5).apply { fill(-3) }
}

fun getDefault(): Int {
    println("Invoked")
    return 0
}
