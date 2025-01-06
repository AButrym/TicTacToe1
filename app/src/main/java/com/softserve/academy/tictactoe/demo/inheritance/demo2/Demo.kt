package com.softserve.academy.tictactoe.demo.inheritance.demo2

interface Fisher {
    fun catchSomeFish()
    fun concrete() = println("Fisher::concrete")
    val name: String get() = "Some Fisher"
}

interface Hunter {
    fun chase() // = println("Hunter is chasing a fox")
    val name: String get() = "Some Hunter"
}

//open class Named(open val name: String)

class John : /*Named("N/A"),*/ Hunter, Fisher {
    override fun chase() {
        TODO("Not yet implemented")
    }

    override fun catchSomeFish() {
        TODO("Not yet implemented")
    }

    override val name: String
        get() =
            super<Hunter>.name + super<Fisher>.name
}

fun main() {
    println(John().name)
    John().concrete()
}
