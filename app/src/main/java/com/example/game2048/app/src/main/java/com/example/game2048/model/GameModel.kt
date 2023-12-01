package com.example.game2048.model

import android.util.Log
import kotlinx.coroutines.withTimeout

class GameModel {

    constructor() {
        restartGame()
    }

    private var _score = 0;
    val score: Int
        get() = this._score

    private var _gameState = GameState.START
    val gameState: GameState
        get() = this._gameState

    var name = ""

    private var _grid = mutableListOf(
        mutableListOf(0,0,0,0),
        mutableListOf(0,0,0,0),
        mutableListOf(0,0,0,0),
        mutableListOf(0,0,0,0))
    val grid: List<List<Int>>
        get() = this._grid;

    private var lastSwipe = false

    fun handleDirection(direction: Direction) {
        if(findEqualNeighbours()) {
            if(lastSwipe) {
                _gameState = GameState.LOST
            } else {
                lastSwipe = true
            }
        }
        when(direction){
            Direction.LEFT -> shiftLeft()
            Direction.RIGHT -> shiftRight()
            Direction.UP -> shiftUp()
            Direction.DOWN -> shiftDown()
            else -> Log.d("", "")
        }
        addNumber()
    }

    fun shiftLeft(){
        var newGrid = mutableListOf<MutableList<Int>>()
        _grid.forEach{it -> newGrid.add(it.reversed().toMutableList())}
        _grid = newGrid
        shiftRight()
        newGrid = mutableListOf<MutableList<Int>>()
        _grid.forEach{it -> newGrid.add(it.reversed().toMutableList())}
        _grid = newGrid
    }

    fun shiftRight(): GameModel {


        for (row in _grid) {
            // Filter out non-zero elements
            val nonZeroElements = row.filter { it != 0 }.toMutableList()
            //TODO: Verdopple gleiche in der Liste
            mergeAdjacentDuplicates(nonZeroElements);
            val zerosToAdd = _grid.size - nonZeroElements.size
            val newRow = MutableList(_grid.size) { if (it < zerosToAdd) 0 else nonZeroElements[it - zerosToAdd] }
            row.clear()
            row.addAll(newRow)
        }

        return this
    }

    fun mergeAdjacentDuplicates(numbers: MutableList<Int>) {
        var i = 0
        while (i < numbers.size - 1) {
            if (numbers[i] == numbers[i + 1]) {
                // If two adjacent numbers are equal, multiply one by 2 and remove the other
                numbers[i] *= 2
                _score += numbers[i]
                numbers.removeAt(i + 1)
            } else {
                // Move to the next pair of elements
                i++
            }
        }
    }

    fun shiftLeft(matrix: Array<IntArray>): Array<IntArray> {
        val result = Array(4) { IntArray(4) }

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                result[i][j] = if (j == 0) matrix[i][3] else matrix[i][j - 1]
            }
        }

        return result
    }

    private fun findEqualNeighbours(): Boolean {
        val equalNeighbors = mutableListOf<Pair<Int, Int>>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                // Check right neighbor
                if (j + 1 < grid[i].size && grid[i][j] == grid[i][j + 1]) {
                    return false
                }

                // Check bottom neighbor
                if (i + 1 < grid.size && grid[i][j] == grid[i + 1][j]) {
                    return false
                }
            }
        }
        return true
    }

    fun shiftUp() {
        rotateGrid()
        shiftRight()
        rotateGrid()
        rotateGrid()
        rotateGrid()
    }

    fun shiftDown() {
        rotateGrid()
        rotateGrid()
        rotateGrid()
        shiftRight()
        rotateGrid()
    }

    fun restartGame() {
        lastSwipe = false
        _score = 0
        _grid = mutableListOf(
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0),
            mutableListOf(0,0,0,0))
        addNumber()
        addNumber()
    }

    private fun addNumber(){
        if(gameOver()) {
            return
        }

        var column = -1
        var row = -1

        do {
            column = Math.floor(Math.random() * 4).toInt()
            row = Math.floor(Math.random() * 4).toInt()
        } while (_grid.get(column).get(row) != 0)
        _grid.get(column).set(row,if (Math.random() < 0.5) 2 else 4)
    }

    private fun gameOver(): Boolean {
        var gameOver = false
        _grid.forEach {
            gameOver = it.filter { it == 0 }.size > 0
            if(gameOver) {
                return false
            }
        }

        return true
    }

    private fun rotateGrid() {
        // Transpose the matrix
        for (i in _grid.indices) {
            for (j in i until _grid[0].size) {
                val temp = _grid[i][j]
                _grid[i][j] = _grid[j][i]
                _grid[j][i] = temp
            }
        }

        // Reverse the order of elements in each row
        for (row in _grid) {
            row.reverse()
        }
    }

    fun startGame() {
        _gameState = GameState.RUNNING
        restartGame()
    }

}