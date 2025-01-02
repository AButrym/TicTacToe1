package com.softserve.academy.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softserve.academy.tictactoe.model.CellState
import com.softserve.academy.tictactoe.model.Field
import com.softserve.academy.tictactoe.model.click
import com.softserve.academy.tictactoe.model.emptyField
import com.softserve.academy.tictactoe.model.ix
import com.softserve.academy.tictactoe.model.rnd
import com.softserve.academy.tictactoe.model.toField
import com.softserve.academy.tictactoe.ui.theme.TicTacToeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var field by remember { mutableStateOf(emptyField) }

    fun onCellClick(iRow: Int, iCol: Int) {
        field = field.click(iRow, iCol)
    }

    fun onReset() {
        field = emptyField
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Title(modifier = Modifier.align(Alignment.TopCenter))
        Grid(field = field,
            onCellClick = ::onCellClick,
            modifier = Modifier.align(Alignment.Center))
        ResetButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = ::onReset
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
         onCellClick: (Int, Int) -> Unit = {_,_ -> },
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
                        onClick = { onCellClick(iRow, iCol) }
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

@Preview(showBackground = true, backgroundColor = 0xFFE0EECF, showSystemUi = true, locale = "uk")
@Composable
fun MainScreenPreviewUK() {
    MainScreen()
}
//@Preview(showBackground = true, backgroundColor = 0xFFE0EECF, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
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
