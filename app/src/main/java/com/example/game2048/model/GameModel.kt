package com.example.game2048.model

class GameModel {

private var _grid = mutableListOf(mutableListOf(2,4,8,16), mutableListOf(2,2,2,2), mutableListOf(0,4,4,0), mutableListOf(0,0,2,2))
    val grid: List<List<Int>>
        get() = this._grid;

    fun shiftLeft(){
        var clonedList = _grid.toMutableList()
        clonedList.forEach{it -> it.reversed()}
        _grid = clonedList
        shiftRight()
        clonedList = _grid.toMutableList()
        clonedList.forEach{it -> it.reversed()}
        _grid = clonedList
    }
    fun shiftRight(): GameModel {
        val clonedList = _grid.toMutableList()

        for (row in clonedList) {
            // Filter out non-zero elements
            val nonZeroElements = row.filter { it != 0 }.toMutableList()
            //TODO: Verdopple gleiche in der Liste
            mergeAdjacentDuplicates(nonZeroElements);

            // Calculate the number of zeros to be added
            val zerosToAdd = clonedList.size - nonZeroElements.size

            // Create a new row with zeros followed by non-zero elements
            val newRow = MutableList(clonedList.size) { if (it < zerosToAdd) 0 else nonZeroElements[it - zerosToAdd] }

            // Update the original row with the new row
            row.clear()
            row.addAll(newRow)
        }

        _grid = clonedList
        return this
    }

    fun mergeAdjacentDuplicates(numbers: MutableList<Int>) {
        var i = 0
        while (i < numbers.size - 1) {
            if (numbers[i] == numbers[i + 1]) {
                // If two adjacent numbers are equal, multiply one by 2 and remove the other
                numbers[i] *= 2
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

    fun shiftUp(matrix: Array<IntArray>): Array<IntArray> {
        val result = Array(4) { IntArray(4) }

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                result[i][j] = if (i == 0) matrix[3][j] else matrix[i - 1][j]
            }
        }

        return result
    }

    fun shiftDown(matrix: Array<IntArray>): Array<IntArray> {
        val result = Array(4) { IntArray(4) }

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                result[i][j] = if (i == 3) matrix[0][j] else matrix[i + 1][j]
            }
        }

        return result
    }

}