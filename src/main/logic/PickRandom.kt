package main.logic

import main.components.Board
import main.components.Point

class PickRandom(
        board: Board
) : LogicEngine(board) {
    override fun move() {
        val movePoint = pickRandomPoint()
        return processMove(movePoint)
    }

    override fun moveVerbose() {
        val movePoint = pickRandomPoint()
        println("random: " + movePoint)
        return processMove(movePoint)
    }

    private fun pickRandomPoint(): Point {
        var tokenPoint = Point(0, 0)
        while (tokenPoint in board.hitsSpace || tokenPoint in board.misses) {
            tokenPoint = tokenPoint.randomPoint(board.width, board.height)
        }
        return tokenPoint
    }

    override fun processMove(movePoint: Point) {
        if (movePoint in board.shipSpace) {
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
