package com.softserve.academy.tictactoe.demo

fun main() {
    try {
        listOf<Number>(1, 2)
        println("Start")
        foo()
        println("Middle")
    } catch (e: Exception) {
        println(e.message)
        for (stackLine in e.stackTrace) {
            print("${stackLine.methodName} -> ")
        }
        println()
    }
    println("End")
}

fun foo() {
    bar()
}

fun bar() {
    buz()
}

fun buz() {
    println(1 / 0)
}