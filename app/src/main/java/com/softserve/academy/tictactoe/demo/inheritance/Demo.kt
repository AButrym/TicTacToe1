package com.softserve.academy.tictactoe.demo.inheritance

interface I1
interface I2: I1
interface I3: I2, I1

// SOLID

class D: I3
//
//open class D
//open class A: D()
//open class C: D()
//
//class B : A(), I1, I2, I3

open class A(open val name: String) {
    open fun foo() {
        println("Hi from A::foo")
        this.bar() // early time (compile) binding / late binding (runtime)
    }
    open fun bar() {
        println("Hi from A::bar")
    }
}

class B(name: String) : A(name) {
    override val name: String
        get() = super.name.uppercase()

    fun buz() {
        println("B.name = $name  A.name = ${super.name}")
    }
//    override fun foo() {
//        println("Hi from B::foo")
//    }
    override fun bar() {
        println("Hi from B::bar")
    }
}


fun main() {
    val b: B = B("Bob")
    b.foo()
    println(b.buz())
}

// OOP: Encapsulation, Inheritance, Polymorphism,