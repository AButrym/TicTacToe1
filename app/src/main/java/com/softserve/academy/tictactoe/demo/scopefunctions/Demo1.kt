package com.softserve.academy.tictactoe.demo.scopefunctions

/*
*       object   result
* this   apply    run
* it     also     let
* arg             with
*  -              run
* */


/**
 * My cool class A
 */
data class A(val name: String, var age: Int = 0) {
    fun foo() = println(this)
}

fun main() {
    fun bar(): Int = run { println("Hello"); 12 }

    val res1 = A("Alice").let { println(it.age) }
    val res2 = A("Alice").run { println(age) }
    val res3 = with(A("Alice")) { println(age) }
    val res11 = A("Alice").let { println(it) }
    val res21 = A("Alice").run { println(this) }
    /////////////////////
    val o = A("Bob")
    o.age = 12
    o.foo()
    with(o) {
        age = 12
        foo()
    }
    /////////////////////
    val o1 = A("Bob").apply { age = 12 }
    /////////////////////
    var (a, b) = 1 to 2
    println("a = $a, b = $b")
    a = b.also { b = a }
    println("a = $a, b = $b")
}
