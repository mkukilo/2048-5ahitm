package com.example.game2048.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.game2048.Direction
import com.example.game2048.model.GameModel

class GameViewModel: ViewModel() {
    fun handleDirection(direction: Direction) {
        _grid.value = listOf()
        when(direction){
            Direction.LEFT -> _gameModel.shiftLeft()
            Direction.RIGHT -> _gameModel.shiftRight()
            else -> Log.d("", "")
        }
        //_gameModel.shiftRight()
        _grid.value = _gameModel.grid
        //_gameModel.value.grid = listOf<>()
    }

    private var _gameModel = GameModel()
    /*val gameModel: MutableState<GameModel>
        get() = this._gameModel*/

    private var _grid = mutableStateOf(_gameModel.grid)

    val grid: MutableState<List<List<Int>>>
        get() = this._grid
}