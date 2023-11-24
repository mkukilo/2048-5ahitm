package com.example.game2048.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.game2048.model.GameModel

class GameViewModel: ViewModel() {
 private var _gameModel = mutableStateOf(GameModel())
val gameModel: MutableState<GameModel>
    get() = this._gameModel;


}