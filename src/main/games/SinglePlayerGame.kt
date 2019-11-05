package main.games

import main.components.Board
import main.logic.*

data class SinglePlayerGame(
        val width: Int,
        val height: Int,
        val logicEngineString: String
) {
    var moveNum: Int = 0
    private var board = Board(1, 1, width, height)
    private var logicEngine = setLogicEngine(logicEngineString)

    fun play() {
        while (!board.game_over) {
            board.printBoard()
            logicEngine.move()
            moveNum++
        }
        board.printBoard()
    }

    fun playVerbose() {
        while (!board.game_over) {
            board.printBoard()
            logicEngine.moveVerbose()
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

    private fun setLogicEngine(logicEngineString: String): LogicEngine {
        if ("random".equals(logicEngineString)) {
            logicEngine = PickRandom(board)
        }
        if ("pickClose".equals(logicEngineString)) {
            logicEngine = PickClose(board)
        }
        if ("pickCloseSmartly".equals(logicEngineString)) {
            logicEngine = PickCloseSmartly(board)
        }
        if ("pickCloseStopWhenSunk".equals(logicEngineString)) {
            logicEngine = PickCloseStopWhenSunk(board)
        }
        return logicEngine
    }
}
