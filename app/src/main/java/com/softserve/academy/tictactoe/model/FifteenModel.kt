package com.softserve.academy.tictactoe.model

typealias FifteenField = List<Int>

interface FifteenModel {
    companion object: FifteenModel {
        val finalState = (1..16).toList()
        const val EMPTY = 16
        private fun isFeasible(currentState: FifteenField): Boolean = true // TODO

        val randomState get(): FifteenField {
            var res: FifteenField
            do {
                res = finalState.shuffled()
            } while(!isFeasible(res))
            return res
        }

        override fun transition(currentState: FifteenField, iRow: Int, iCol: Int): FifteenField {
            TODO()
            /**
             * take current state, the client pushes the cell at (iRow, iCol)
             * if this cell has an empty cell neighbor then return a new state where
             * the clicked cell and the neighboring empty one are swapped
             */
        }
    }

    val initialState get() = randomState

    fun isWin(currentState: FifteenField) = currentState == finalState

    fun transition(currentState: FifteenField, iRow: Int, iCol: Int): FifteenField
}