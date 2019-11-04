package main.logic

import main.components.Board
import main.components.Point

class LogicEngineRandom (
    var board: Board
) {
    fun move() {
        val movePoint = pickRandomPoint()
        return processMove(movePoint)
    }

    private fun pickRandomPoint(): Point {
        var tokenPoint = Point(0, 0)
        while (tokenPoint in board.hits_space || tokenPoint in board.misses) {
            tokenPoint = tokenPoint.randomPoint(board.width, board.height)
        }
        return tokenPoint
    }

    fun processMove(movePoint: Point) {
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
}
