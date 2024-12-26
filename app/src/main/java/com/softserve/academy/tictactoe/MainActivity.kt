package com.softserve.academy.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softserve.academy.tictactoe.model.CellState
import com.softserve.academy.tictactoe.model.rnd
import com.softserve.academy.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen1(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen1(modifier: Modifier = Modifier) {
    Text("Hello\nWorld",
        modifier = Modifier
            .padding(10.dp)
//            .size(width = 50.dp, height = 20.dp)
        ,
        fontSize = 16.sp,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Cursive,
        color = Color.Black,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center,
        lineHeight = 30.sp

    )
}

@Composable
fun MainScreen() {
    Grid()
}

@Composable
fun Grid() {
    Column {
        repeat(3) {
            Row {
                repeat(3) {
                    Cell(CellState.rnd)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE0EECF)
@Composable
fun GridPreview() {
    Grid()
}

@Composable
fun Cell(cellState: CellState) {
    val text = when(cellState) {
        CellState.CROSS -> "❌"
        CellState.NOUGHT -> "⭕"
        CellState.EMPTY -> ""
    }
    ElevatedButton(
        onClick = {},
        modifier = Modifier
            .padding(1.dp)
            .size(60.dp),
        shape = MaterialTheme.shapes.extraSmall,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text,
            fontFamily = FontFamily.Monospace,
            fontSize = 40.sp,)
    }
}

@Preview
@Composable
fun CellPreview1() {
    Cell(CellState.EMPTY)
}
@Preview
@Composable
fun CellPreview2() {
    Cell(CellState.CROSS)
}
@Preview
@Composable
fun CellPreview3() {
    Cell(CellState.NOUGHT)
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}
