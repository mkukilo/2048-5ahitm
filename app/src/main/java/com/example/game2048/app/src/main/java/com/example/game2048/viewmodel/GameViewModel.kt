package com.example.game2048.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.game2048.model.Direction
import com.example.game2048.model.GameModel
import com.example.game2048.model.GameState

class GameViewModel: ViewModel() {

    private var _gameModel = GameModel()

    private var _grid = mutableStateOf(_gameModel.grid)
    val grid: MutableState<List<List<Int>>>
        get() = this._grid


    private var _score = mutableStateOf(_gameModel.score)
    val score: MutableState<Int>
        get() = this._score

    private var _gameState = mutableStateOf(_gameModel.gameState)
    val gameState: MutableState<GameState>
        get() = this._gameState

    var name = mutableStateOf(_gameModel.name)

    fun handleDirection(direction: Direction) {
        _grid.value = listOf()
        _score.value = 0
        _gameModel.handleDirection(direction)
        _gameState.value = _gameModel.gameState
        _score.value = _gameModel.score
        _grid.value = _gameModel.grid
    }

    fun restartGame() {
        _gameModel.restartGame()
        _score.value = _gameModel.score
        _grid.value = _gameModel.grid
    }

    fun startGame() {
        _gameModel.startGame()
        _gameState.value = _gameModel.gameState
    }


}