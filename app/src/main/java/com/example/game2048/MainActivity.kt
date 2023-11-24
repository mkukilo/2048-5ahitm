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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.game2048.ui.theme.Game2048Theme
import com.example.game2048.ui.theme.GameBackground
import com.example.game2048.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private var gameModel = viewModels<GameViewModel>()
    private val TAG = "Log:"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                var direction by remember { mutableStateOf(Direction.ZERO) }
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

                               detectDragGestures(
                                   onDrag = {
                                       change, dragAmount ->
                                       change.consume()
                                       val (x,y) = dragAmount;
                                       direction = handleDragEvent(x,y);
                                   },
                                   onDragEnd = {
                                       gameModel.value.handleDirection(direction)
                                       //TODO handle direction
                                       Log.d(TAG, direction.toString());
                                   }

                               )

                                    // Reset swipeLogged after a certain time period (for example, 1 second)


                            }
                    )
                            {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            gameModel.value.grid.value.forEach{
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

    private fun handleDragEvent(x: Float, y: Float): Direction {
        when {
            x > 0 ->{ return Direction.RIGHT}
            x < 0 ->{ return Direction.LEFT}
        }
        when {
            y > 0 -> {return Direction.DOWN}
            y < 0 -> {return Direction.UP}
        }
        return Direction.ZERO
    }
}