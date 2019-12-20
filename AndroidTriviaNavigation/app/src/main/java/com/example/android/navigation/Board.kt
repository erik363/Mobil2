package com.example.android.navigation

class Board {

    companion object {
        const val PLAYER1 = "O"
        const val PLAYER2 = "X"
    }

    fun playerOneWon(): Boolean{
        var count = 0
        for(i in 0..2){
            for(j in 0..2) {
                if( board[i][j] == PLAYER1) count++
            }
            if (count == 3) return true
            else count = 0
        }
        count = 0
        for(i in 0..2){
            var idx = 0
            for(j in 0..2) {
                if( board[j][i] == PLAYER1) count++

            }
            if (count == 3) return true
            else count = 0
        }
        count = 0

        return false
    }

    val  board = Array(3){ arrayOfNulls<String>(3)}

    fun move(cell: Cell, player: String){
        board[cell.i][cell.j] = player
    }

}