package com.softserve.academy.tictactoe.demo

fun main() {
    var day1: DayOfWeek = DayOfWeek.Monday
    println("day1.name = ${day1.name}")
    println("day1.ordinal = ${day1.ordinal}")
    day1 = DayOfWeek.Tuesday
    println("=".repeat(20))
    DayOfWeek.entries.forEach(::println)
    DayOfWeek.values().forEach(::println)
    println("day1.name = ${day1.name}")
    println("day1.ordinal = ${day1.ordinal}")
    val day3 = runCatching { DayOfWeek.valueOf("Tueday") }.getOrElse { DayOfWeek.Monday }
    println(day3)
//    val day2: DayOfWeek2 = DayOfWeek2.Monday
}

enum class DayOfWeek {
    Monday, Tuesday
}

abstract class Abstr {
    abstract fun foo(): Int
}

class B12 : Abstr() {
    override fun foo(): Int {
        TODO("Not yet implemented")
    }
}

sealed class DayOfWeek2 private constructor() {
    data object Monday : DayOfWeek2()
    data object Tuesday : DayOfWeek2()
}