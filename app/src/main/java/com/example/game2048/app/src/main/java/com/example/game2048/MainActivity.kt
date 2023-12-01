package com.example.game2048

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.game2048.model.Direction
import com.example.game2048.model.GameModel
import com.example.game2048.model.GameState
import com.example.game2048.ui.theme.Game2048Theme
import com.example.game2048.ui.theme.GameBackground
import com.example.game2048.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private var gameViewModelInit = viewModels<GameViewModel>()
    private lateinit var gameViewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        gameViewModel = gameViewModelInit.value
        super.onCreate(savedInstanceState)
        setContent {
            Game2048Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GameBackground),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    when(gameViewModel.gameState.value) {
                        GameState.RUNNING -> GameScreen(gameViewModel = gameViewModel)
                        GameState.LOST -> LostScreen(gameViewModel = gameViewModel)
                        GameState.WON -> Text(text = "2asdf")
                        GameState.START -> StartScreen(gameViewModel = gameViewModel)
                    }
                }
            }
        }
    }

    
}