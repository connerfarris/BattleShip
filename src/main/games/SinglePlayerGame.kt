package main.games

import main.components.Board
import main.logic.LogicEngineRandom

data class SinglePlayerGame(
        val width: Int,
        val height: Int
) {
    var moveNum: Int = 0
    private var board = Board(1, 1, width, height)
    private var logicEngine = LogicEngineRandom(board)

    fun play() {
        while (!board.game_over) {
            board.printBoard()
            logicEngine.move()
            moveNum++
        }
        board.printBoard()
    }

    fun playNoPrint() {
        while (!board.game_over) {
            logicEngine.move()
            moveNum++
        }
    }
}
