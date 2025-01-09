package com.softserve.academy.tictactoe.demo.inheritance

sealed class Animal

class Chicken: Animal() {
    val nEggs: Int get() = 5
}

class Pig: Animal()

fun main() {
    val animal: Animal = listOf(Pig(), Chicken()).random()

    var message = when (animal) {
        is Chicken -> "This chicken has ${animal.nEggs} eggs"
        is Pig -> "This is a pig, roh, roh!"
    }

    println("This chicken has ${(animal as? Chicken)?.nEggs ?: 0} eggs")
}