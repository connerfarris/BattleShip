package main.logic

import main.components.Board

abstract class LogicEngine (
        val board: Board
) {
    abstract fun move()
}