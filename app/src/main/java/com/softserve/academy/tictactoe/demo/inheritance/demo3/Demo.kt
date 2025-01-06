package com.softserve.academy.tictactoe.demo.inheritance.demo3

class BankAccount(private var sum: Int) {
    fun display() = println("sum = $sum")

    class Transaction() {
        fun pay(acc: BankAccount, amount: Int) {
            acc.sum -= amount
            acc.display()
        }
    }
}