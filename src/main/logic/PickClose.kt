package main.logic

import main.components.Board
import main.components.Point

class PickClose(
        board: Board
) : LogicEngine(board) {
    override fun move() {
        var movePoint: Point = getPossibleShipPoint()
        if (movePoint == Point(-1, -1)) {
            movePoint = pickRandomPoint()
        }
        return processMove(movePoint)
    }

    override fun moveVerbose() {
        var movePoint: Point = getPossibleShipPoint()
        if (movePoint == Point(-1, -1)) {
            movePoint = pickRandomPoint()
            println("random: " + movePoint)
        } else {
            println("pick: " + movePoint)
        }
        return processMove(movePoint)
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

    private fun pickRandomPoint(): Point {
        var tokenPoint = Point(0, 0)
        while (tokenPoint in board.hitsSpace || tokenPoint in board.misses) {
            tokenPoint = tokenPoint.randomPoint(board.width, board.height)
        }
        return tokenPoint
    }

    private fun getPossibleShipPoint(): Point {
        for (hit in board.hitsSpace) {
            val tempPointUp = Point(hit.row - 1, hit.col)
            val tempPointDown = Point(hit.row + 1, hit.col)
            val tempPointLeft = Point(hit.row, hit.col - 1)
            val tempPointRight = Point(hit.row, hit.col + 1)
            if (tempPointUp in board && tempPointUp !in board.hitsSpace && tempPointUp !in board.misses) {
                return tempPointUp
            }
            if (tempPointDown in board && tempPointDown !in board.hitsSpace && tempPointDown !in board.misses) {
                return tempPointDown
            }
            if (tempPointLeft in board && tempPointLeft !in board.hitsSpace && tempPointLeft !in board.misses) {
                return tempPointLeft
            }
            if (tempPointRight in board && tempPointRight !in board.hitsSpace && tempPointRight !in board.misses) {
                return tempPointRight
            }
        }
        return Point(-1, -1)
    }


}