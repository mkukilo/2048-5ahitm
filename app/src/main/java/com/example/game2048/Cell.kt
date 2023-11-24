package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.game2048.ui.theme.Game2Background
import com.example.game2048.ui.theme.Game2Color

@Composable
fun Cell(value:Int) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.background(evaluateBackground(value))) {
        Text(text = value.toString(),
            color = evaluateForegroundColor(value)
        )
    }
}

fun evaluateBackground(value:Int): Color {
    return when (value) {
        2-> Game2Background
        else -> Game2Background
    }
}
fun evaluateForegroundColor(value:Int): Color {
    return when (value) {
        2-> Game2Color
        else -> Game2Color
    }
}