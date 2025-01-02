package com.softserve.academy.tictactoe

import com.softserve.academy.tictactoe.model.GameState
import com.softserve.academy.tictactoe.model.Player
import com.softserve.academy.tictactoe.model.click
import com.softserve.academy.tictactoe.model.gameState
import com.softserve.academy.tictactoe.model.nextPlayer
import com.softserve.academy.tictactoe.model.toField
import org.junit.Test
import org.junit.Assert.*

class CliTest {
    @Test
    fun `test gameState correctly estimate Illegal state`() {
        // Arrange
        val field = "XXXXXXXXX".toField()
        val expected = GameState.ILLEGAL
        // Act
        val res = field.gameState
        // Assert
        assertSame(expected, res)
    }
    @Test
    fun `gameState shows win for main diagonal filled with crosses`() {
        // Arrange
        val field = "X00|_X_|__X".toField()
        val expected = GameState.CROSS_WIN
        // Act
        val res = field.gameState
        // Assert
        assertSame(expected, res)
    }

    @Test
    fun `when there is a single cross on the field then next player should be nought`() {
        val field = "X________".toField()

        val res = field.nextPlayer

        assertEquals(Player.NOUGHT, res)
    }

    @Test
    fun testClick1() {
        val initial = "X0_______".toField()
        val expected = "X0X______".toField()

        val afterClick = initial.click(0, 2)

        assertEquals(expected.toList(), afterClick.toList())
    }

    @Test
    fun testClick2() {
        val initial = "X0X______".toField()
        val expected = initial

        val afterClick = initial.click(0, 2)

        assertEquals(expected.toList(), afterClick.toList())
    }
}