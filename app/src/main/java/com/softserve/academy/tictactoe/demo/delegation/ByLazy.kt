package com.softserve.academy.tictactoe.demo.delegation

fun generateName(): String {
    println("=> generateName invoked")
    return listOf("Tom", "Puss", "Garfield").random()
}

class Cat {
    val name: String by lazy { generateName() }
}

fun main() {
    println("Start")
    val cat = Cat()
    println("Middle")
    println("Cat's name is ${cat.name}")
    println("Cat's name is ${cat.name}")
    println("Cat's name is ${cat.name}")
    val cat2 = Cat()
    println("Middle")
    println("Cat's name is ${cat2.name}")
    println("Cat's name is ${cat2.name}")
    println("Cat's name is ${cat2.name}")

}