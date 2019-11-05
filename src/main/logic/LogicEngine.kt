package main.logic

import main.components.Board
import main.components.Point

abstract class LogicEngine(
        val board: Board
) {
    abstract fun move()
    abstract fun moveVerbose()
    abstract fun processMove(movePoint: Point)
}
