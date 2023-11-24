package com.example.game2048

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game2048.ui.theme.Game2048Theme
import com.example.game2048.ui.theme.GameBackground
import com.example.game2048.viewmodel.GameViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var gameModel = viewModels<GameViewModel>()
    private val TAG = "Log:"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(GameBackground)
                            .padding(16.dp)
                            .pointerInput(Unit) {
                                var swipeLogged = false

                                detectDragGestures { change, dragAmount ->
                                    change.consume()

                                    if (!swipeLogged) {
                                        val (x, y) = dragAmount
                                        when {
                                            x > 0 -> {
                                                Log.d(TAG, "right")
                                                swipeLogged = true
                                            }
                                            x < 0 -> {
                                                Log.d(TAG, "left")
                                                swipeLogged = true
                                            }
                                        }
                                        when {
                                            y > 0 -> {
                                                Log.d(TAG, "down")
                                                swipeLogged = true
                                            }
                                            y < 0 -> {
                                                Log.d(TAG, "up")
                                                swipeLogged = true
                                            }
                                        }
                                    }

                                    // Reset swipeLogged after a certain time period (for example, 1 second)
                                    if (!swipeLogged) {
                                        GlobalScope.launch {
                                            delay(1000) // 1000 milliseconds (1 second)
                                            swipeLogged = false
                                        }
                                    }
                                }
                            }
                    )
                            {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            gameModel.value.gameModel.value.grid.forEach{
                                    cellList ->
                                run {
                                    Row {
                                        cellList.forEach { cellValue ->
                                            run {
                                                Cell(value = cellValue)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}