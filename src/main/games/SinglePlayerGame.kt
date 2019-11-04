package main

data class SinglePlayerGame (
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
}
