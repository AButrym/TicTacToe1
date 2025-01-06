package com.softserve.academy.tictactoe.demo.inheritance.collection

interface StringIterator {
    val hasNext: Boolean
    val next: String
}

interface ListOfStrings {
    val size: Int
    val isEmpty: Boolean get() = (size == 0)
    fun add(str: String)
    fun clear()
    fun remove(str: String)
    fun indexOf(str: String): Int

    fun display() {
        val iterator = iterator()
        while (iterator.hasNext) {
            print(iterator.next)
            if (iterator.hasNext) {
                print(" -> ")
            }
        }
    }

    fun iterator(): StringIterator
}

fun main() {
    val list: ListOfStrings = SingleLinkedList()
    list.add("Alice")
    list.add("Bob")
    list.add("John")
    list.display()
}

class SingleLinkedList: ListOfStrings {
    override var size: Int = 0
        private set

    private var head: Node? = null
    private var tail: Node? = null

    private class Node(val str: String, var next: Node? = null)

    override fun add(str: String) {
        val node = Node(str)
        if (isEmpty) {
            head = node
            tail = node
        } else {
            tail!!.next = node
            tail = node
        }
        size++
    }

    override fun iterator(): StringIterator =
        this.ListIterator()

    inner class ListIterator: StringIterator {
        private var node: Node? = head

        override val hasNext: Boolean
            get() = node != null
        override val next: String
            get() {
                require(hasNext)
                val res = node!!.str
                node = node?.next
                return res
            }
    }

//    override fun display() {
//        var node = head
//        while (node != null) {
//            print("${node.str}")
//            if (node.next != null) {
//                print(" -> ")
//            }
//            node = node.next
//        }
//    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun remove(str: String) {
        TODO("Not yet implemented")
    }

    override fun indexOf(str: String): Int {
        TODO("Not yet implemented")
    }
}