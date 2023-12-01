package com.example.game2048

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.ui.theme.Game1024Background
import com.example.game2048.ui.theme.Game128Background
import com.example.game2048.ui.theme.Game16Background
import com.example.game2048.ui.theme.Game2048Background
import com.example.game2048.ui.theme.Game256Background
import com.example.game2048.ui.theme.Game2Background
import com.example.game2048.ui.theme.Game2Color
import com.example.game2048.ui.theme.Game32Background
import com.example.game2048.ui.theme.Game4Background
import com.example.game2048.ui.theme.Game4Color
import com.example.game2048.ui.theme.Game512Background
import com.example.game2048.ui.theme.Game64Background
import com.example.game2048.ui.theme.Game8Background
import com.example.game2048.ui.theme.Game8Color

@Composable
fun Cell(value:Int) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .size(80.dp)
            .background(evaluateBackground(value))
    ) {
        Text(
            text = if (value > 0) value.toString()
            else "",
            color = evaluateForegroundColor(value),
            fontSize = setFontSize(value)

        )
    }
}

fun setFontSize(number: Int): TextUnit {
    return if (number >= 100) {
        28.sp
    } else {
        36.sp
    }
}

fun evaluateBackground(value:Int): Color {

    return when (value) {
        2-> Game2Background
        4-> Game4Background
        8-> Game8Background
        16-> Game16Background
        32-> Game32Background
        64-> Game64Background
        128-> Game128Background
        256-> Game256Background
        512-> Game512Background
        1024-> Game1024Background
        2048-> Game2048Background
        else -> Game2Background
    }
}
fun evaluateForegroundColor(value:Int): Color {
    return when (value) {
        2-> Game2Color
        4-> Game4Color


        else -> Game8Color
    }
}