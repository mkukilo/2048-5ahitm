package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.game2048.model.GameState
import com.example.game2048.ui.theme.PurpleGrey80
import com.example.game2048.viewmodel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(gameViewModel: GameViewModel) {
    Column (
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xF6F6F6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var text by remember { gameViewModel.name }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") },
        )
        Button(
            onClick = { gameViewModel.startGame() },
            enabled = gameViewModel.name.value.length != 0
        ) {
            Text(text = "Start Game")
        }
    }
}