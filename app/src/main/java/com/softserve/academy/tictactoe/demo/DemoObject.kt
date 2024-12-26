package com.softserve.academy.tictactoe.demo

// singleton
object WarriorProps {
    object Knight {
        const val HEALTH = 30
        const val ATTACK = 4
    }
    object Lancer {
        const val HEALTH = 50
    }
}

class Lancer private constructor(val name: String) {
    var health: Int = Props.HEALTH
        private set

    companion object {
        const val type = "Lancer"
        operator fun invoke(): Lancer {
            println("Inside companion invoke")
            return Lancer("Lancelot the Great")
        }
    }

    object Props {
        const val HEALTH = 50
    }

    object Factory {
        private var count = 0
        fun createLancer(): Lancer {
            return Lancer("Lancer#${++count}")
        }
    }

    override fun toString(): String {
        return "Lancer(name='$name', health=$health)"
    }
}

class BaseWithCompanion {
    companion object {}
}

fun BaseWithCompanion.Companion.foo() = "foo" // "static" extension

fun main() {
    BaseWithCompanion.foo()
    Lancer.Props.HEALTH
    Lancer.type
    val lancelot1: Lancer = Lancer.Factory.createLancer()
    val lancelot2: Lancer = Lancer.Factory.createLancer()
    val lancelot3: Lancer = Lancer.Factory.createLancer()
    println(lancelot3.name)
    val warrior1 = Lancer.Companion.invoke()
    val warrior2 = Lancer()
    println(warrior2)
}