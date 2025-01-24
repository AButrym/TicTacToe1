package com.softserve.academy.tictactoe.demo

class MyIterable : Iterable<String> {
    override fun iterator(): Iterator<String> {
        return listOf("Alice", "Bob").iterator()
    }
}

fun main() {
    val o = MyIterable()
    for (el in o) {
        println(el)
    }
    o.forEach(::println)
}