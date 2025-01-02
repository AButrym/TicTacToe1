package com.softserve.academy.tictactoe.model

import com.softserve.academy.tictactoe.model.GameState.*

typealias Field = List<CellState>

fun String.toField(): Field {
    val res = mutableListOf<CellState>()
    for (ch in this) {
       when(ch) {
           'x', 'X' -> res.add(CellState.CROSS)
           '0', 'o', 'O' -> res.add(CellState.NOUGHT)
           '_', ' ' -> res.add(CellState.EMPTY)
       }
    }
    return res
}

const val DIM = 3

operator fun Field.get(row: Int, col: Int) = this[ix(row, col)]
//operator fun Field.set(row: Int, col: Int, cell: CellState) {
//    this[ix(row, col)] = cell
//}

fun ix(row: Int, col: Int) = row * DIM + col

fun printField(field: Field) {
    repeat(DIM) { row ->
        repeat(DIM) { col ->
            print("${field[row, col]} ")
        }
        println()
    }
}

enum class CellState {
    EMPTY, CROSS, NOUGHT;

    companion object {}

    override fun toString(): String = when(this) {
        EMPTY -> "_"
        CROSS -> "X"
        NOUGHT -> "0"
    }
}

val CellState.Companion.rnd get() = CellState.entries.random()

val emptyField = "___|___|___".toField()

enum class GameState {
    IN_PROGRESS, CROSS_WIN, NOUGHT_WIN, DRAW, ILLEGAL
}

enum class Player {
    CROSS, NOUGHT
}

//fun Player.toChar(): Char = when(this) {
//    Player.CROSS -> 'X'
//    Player.NOUGHT -> '0'
//}
val Player.mark get() = when(this) {
    Player.CROSS -> CellState.CROSS
    Player.NOUGHT -> CellState.NOUGHT
}

//fun gameState(field: Field): GameState
val Field.gameState: GameState get() {
    // 0 1 2
    // 3 4 5
    // 6 7 8
    val lines = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),
        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),
        arrayOf(0, 4, 8),
        arrayOf(2, 4, 6),
    )
    val nX = this.count { it == CellState.CROSS }
    val n0 = this.count { it == CellState.NOUGHT }
    if ((nX == n0 || nX == n0 + 1).not()) {
        return ILLEGAL
    }
    if (nX + n0 == DIM * DIM) return DRAW
    for (line in lines) {
        when {
            line.all { this[it] == CellState.CROSS }  -> return CROSS_WIN
            line.all { this[it] == CellState.NOUGHT } -> return NOUGHT_WIN
        }
    }
    return IN_PROGRESS
}

val Field.nextPlayer: Player get() {
    val nX = this.count { it == CellState.CROSS }
    val n0 = this.count { it == CellState.NOUGHT }
    return if (nX > n0) Player.NOUGHT else Player.CROSS
}

fun Field.click(row: Int, col: Int): Field {
    if (this[ix(row, col)] != CellState.EMPTY || gameState != IN_PROGRESS) {
        return this
    }
    val res = this.toMutableList()
    res[ix(row, col)] = nextPlayer.mark
    return res
}

fun main() {
    val data: Field = "0X0|X0X|0X0".toField()
    printField(data)
//    println(gameState(data))
    println(data.gameState)
    println("XXX|XXX|XXX".toField().gameState)
    println("XXX_0_00_".toField().gameState)
    println("000_X_XX_".toField().gameState)
    println("X________".toField().nextPlayer) // > NOUGHT
    println("X0_______".toField().nextPlayer) // > CROSS
    println("X0_______".toField().click(0, 2).toList() == "X0X______".toField().toList())
}