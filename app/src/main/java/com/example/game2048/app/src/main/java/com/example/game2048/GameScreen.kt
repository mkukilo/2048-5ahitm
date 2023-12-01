package com.example.game2048

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.game2048.model.Direction
import com.example.game2048.ui.theme.GameBackground
import com.example.game2048.viewmodel.GameViewModel

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    var direction by remember { mutableStateOf(Direction.ZERO) }
    val TAG = "Log:"

    Column (
        modifier = Modifier.fillMaxWidth().background(GameBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        ScoreBar(gameViewModel = gameViewModel)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .pointerInput(Unit) {
                    var swipeLogged = false

                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            val (x, y) = dragAmount;
                            direction = handleDragEvent(x, y);
                        },
                        onDragEnd = {
                            gameViewModel.handleDirection(direction)
                            //TODO handle direction
                            Log.d(TAG, direction.toString());
                        }

                    )
                }
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GameBackground),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                gameViewModel.grid.value.forEach { cellList ->
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
        Button(onClick = { gameViewModel.restartGame() }

        ) {
            Text(text = "Click to restart")
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