package com.softserve.academy.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softserve.academy.tictactoe.TicTacToeAction.*
import com.softserve.academy.tictactoe.model.CellState
import com.softserve.academy.tictactoe.model.Field
import com.softserve.academy.tictactoe.model.GameState
import com.softserve.academy.tictactoe.model.click
import com.softserve.academy.tictactoe.model.emptyField
import com.softserve.academy.tictactoe.model.gameState
import com.softserve.academy.tictactoe.model.ix
import com.softserve.academy.tictactoe.model.toField
import com.softserve.academy.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StateHolder(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Model-View-Intent
sealed interface TicTacToeAction {
    data object ResetAction : TicTacToeAction
    data class CellClickAction(val iRow: Int, val iCol: Int) : TicTacToeAction
}

typealias ActionCallback = (TicTacToeAction) -> Unit


class TicTacToeViewModel() : ViewModel() {
    var field by mutableStateOf(emptyField)
    val gameState by derivedStateOf { field.gameState }

    fun onAction(action: TicTacToeAction) {
        when (action) {
            is ResetAction -> {
                field = emptyField
            }
            is CellClickAction ->
                with(action) { field = field.click(iRow, iCol) }
        }
    }
}

@Composable
fun StateHolder(modifier: Modifier = Modifier, model: TicTacToeViewModel = viewModel()) {
    MainScreen(model.field, model.gameState, modifier,
        model::onAction
    )
}

@Composable
fun MainScreen(field: Field,
               gameState: GameState,
               modifier: Modifier = Modifier,
               onAction: ActionCallback = { }
               ) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Title(modifier = Modifier.align(Alignment.TopCenter))
        Grid(field = field,
            onAction = onAction,
            modifier = Modifier.align(Alignment.Center))
        ResetButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { onAction(ResetAction) }
        )
        if (gameState != GameState.IN_PROGRESS) {
            WinBanner(gameState, modifier = Modifier
                .align(Alignment.Center)
                .clickable { onAction(ResetAction) }
            )
        }
    }
}

@Composable
fun WinBanner(gameState: GameState, modifier: Modifier = Modifier) {
    val text = when (gameState) {
        GameState.CROSS_WIN -> stringResource(R.string.cross_wins)
        GameState.NOUGHT_WIN -> stringResource(R.string.nought_wins)
        GameState.DRAW -> stringResource(R.string.draw)
        else -> error("Should not get here")
    }
    Box(modifier = modifier
        .border(20.dp, Color.Blue)
        .padding(30.dp)
        .border(20.dp, Color.Green)
        .background(Color.LightGray.copy(alpha = 0.7f))
        .size(300.dp)
    ) {
        Text(text,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 24.sp
            )
    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.tictactoe_game_title),
        modifier = modifier.offset(y = 40.dp),
        style = MaterialTheme.typography.headlineLarge)
}

@Composable
fun ResetButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick,
        modifier = modifier
            .offset(y = (-40).dp)
    ) {
        Text(
            stringResource(R.string.reset),
            style = MaterialTheme.typography.displayLarge)
    }
}

@Composable
fun Grid(field: Field,
         onAction: ActionCallback = { },
    modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .border(2.dp, Color.Black)
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { iRow ->
            Row {
                repeat(3) { iCol ->
                    Cell(field[ix(iRow, iCol)],
                        onClick = { onAction(CellClickAction(iRow, iCol)) }
                        )
                }
            }
        }
    }
}

@Composable
fun Cell(
    cellState: CellState,
    onClick: () -> Unit = {}
) {
    val text = when(cellState) {
        CellState.CROSS -> "❌"
        CellState.NOUGHT -> "⭕"
        CellState.EMPTY -> ""
    }
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(1.dp)
            .size(120.dp),
        shape = MaterialTheme.shapes.extraSmall,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text,
            fontFamily = FontFamily.Monospace,
            fontSize = 96.sp,)
    }
}


//@Preview(showBackground = true, backgroundColor = 0xFFE0EECF)
@Composable
fun GridPreview() {
    Grid("000_X_XX_".toField())
}

//@Preview(showBackground = true, backgroundColor = 0xFFE0EECF, showSystemUi = true, locale = "uk")
@Composable
fun MainScreenPreviewUK() {
    MainScreen(
        field = "X00|_X_|__X".toField(),
        gameState = GameState.CROSS_WIN)
}

@Preview(showBackground = true, backgroundColor = 0xFFE0EECF, showSystemUi = true,
    device = "spec:parent=pixel_5"
)
@Composable
fun MainScreenPreview() {
    StateHolder()
}

//@Preview
@Composable
fun CellPreview1() {
    Cell(CellState.EMPTY)
}
//@Preview
@Composable
fun CellPreview2() {
    Cell(CellState.CROSS)
}
//@Preview
@Composable
fun CellPreview3() {
    Cell(CellState.NOUGHT)
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}
