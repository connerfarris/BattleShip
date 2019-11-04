package main.logic

import main.components.Board
import main.components.Point

class LogicEnginePickClose (
        var board: Board
) {
    fun move() {
        val movePoint = pickRandomPoint()
        return processMove(movePoint)
    }

    private fun processMove(movePoint: Point) {
        if (movePoint in board.ship_space) {
            for (ship in board.ships) {
                if (movePoint in ship) {
                    ship.hits.add(movePoint)
                    board.refreshBoardState()
                    break
                }
            }
        } else {
            board.misses.add(movePoint)
            board.refreshBoardState()
        }
    }

    private fun pickRandomPoint(): Point {
        var tokenPoint = Point(0, 0)
        while (tokenPoint in board.hits_space || tokenPoint in board.misses) {
            tokenPoint = tokenPoint.randomPoint(board.width, board.height)
        }
        return tokenPoint
    }

    private fun shouldPickRandomPoint(): Boolean {
        if (areShipsClose()) {
            return false
        }
        return true
    }

    private fun areShipsClose(): Boolean {
        for (hit in board.hits_space) {
            val tempPointUp = Point(hit.row - 1, hit.col)
            val tempPointDown = Point(hit.row + 1, hit.col)
            val tempPointLeft = Point(hit.row, hit.col - 1)
            val tempPointRight = Point(hit.row, hit.col + 1)
            if (tempPointUp in board && tempPointDown in board && tempPointLeft in board && tempPointRight in board
                    && (tempPointUp in board.hits_space || tempPointUp in board.misses)
                    && (tempPointDown in board.hits_space || tempPointDown in board.misses)
                    && (tempPointLeft in board.hits_space || tempPointLeft in board.misses)
                    && (tempPointRight in board.hits_space || tempPointRight in board.misses)) {
                return false
                break
            }
        }
        return true
    }


}