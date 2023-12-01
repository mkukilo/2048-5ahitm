package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.Purple40
import com.example.game2048.viewmodel.GameViewModel

@Composable
fun ScoreBar(gameViewModel: GameViewModel) {

    Box(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.fillMaxWidth()
            .background(Purple40),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = gameViewModel.name.value,
                fontSize = 36.sp,
                color = Color.White)
            Text(
                text = gameViewModel.score.value.toString(),
                fontSize = 36.sp,
                color = Color.White)
        }
    }

}