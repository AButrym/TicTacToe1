package com.softserve.academy.tictactoe.demo.collections

fun main() {
    val set1 = mutableSetOf(1, 2, 3)
    val set2 = hashSetOf(1, 2, 3)
    set1 -= set2
    val res1 = set1.add(4)
    val res2 = set1.add(44)
    println("res1 = $res1, res2 = $res2")
    println(set1.size)
    println(1 in set1)
    println(set1.contains(1))
    val list1: List<Int> = listOf()
    val list2 = listOf<Int>()
    val list3 = buildList {
        addAll(list1)
        add(1)
        addAll(list2)
    }
    val s = buildString {
        append("Hi")
        append(" world")
    }
    println(s)

    val map1 = mapOf(
        "1" to "one",
        "2" to "two")
    println(map1.getOrDefault("3", "N/A"))
    println(map1["3"] ?: "N/A")
    println(map1["1"])
    for(e in map1.entries) {
        println("k = ${e.key}, v = ${e.value}")
    }
    map1.entries.first().component1()
    for((digit, word) in map1) {
        println("k = $digit, v = $word")
    }
}