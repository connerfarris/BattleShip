package main.logic

import main.components.Board
import main.components.Point
import java.lang.Math.abs

class PickCloseStopWhenSunk(
        board: Board
) : LogicEngine(board) {

    var sunkShipCount: Int = 0

    override fun move() {
        var movePoint: Point
        if (board.hits_space.size == getSizeOfSunkShips(board.sunk_ships)) {
            movePoint = pickRandomPoint()
            sunkShipCount++
        } else {
            movePoint = getPossibleShipPoint()
            if (movePoint == Point(-1, -1)) {
                movePoint = pickRandomPoint()
            }
        }
        return processMove(movePoint)
    }

    override fun moveVerbose() {
        println("sunkShipCount: " + sunkShipCount)
        println("board.sunk_ships.size: " + board.sunk_ships.size)
        println("board.sunk_ships: " + board.sunk_ships)
        println("board.hits_space.size: " + board.hits_space.size)
        println("getSizeOfSunkShips(board.sunk_ships): " + getSizeOfSunkShips(board.sunk_ships))
        var movePoint: Point
        if (/*board.sunk_ships.size != sunkShipCount &&*/ board.hits_space.size == getSizeOfSunkShips(board.sunk_ships)) {
            movePoint = pickRandomPoint()
            sunkShipCount++
            println("random: " + movePoint)
        } else {
            movePoint = getPossibleShipPoint()
            if (movePoint == Point(-1, -1)) {
                movePoint = pickRandomPoint()
                println("random: " + movePoint)
            } else {
                println("pick: " + movePoint)
            }
        }
        return processMove(movePoint)
    }

    override fun processMove(movePoint: Point) {
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
        while ((abs(tokenPoint.row - tokenPoint.col) % 2 == 1) || tokenPoint in board.hits_space || tokenPoint in board.misses) {
            tokenPoint = tokenPoint.randomPoint(board.width, board.height)
        }
        return tokenPoint
    }

    private fun getPossibleShipPoint(): Point {
        for (hit in board.hits_space) {
            val tempPointUp = Point(hit.row - 1, hit.col)
            val tempPointDown = Point(hit.row + 1, hit.col)
            val tempPointLeft = Point(hit.row, hit.col - 1)
            val tempPointRight = Point(hit.row, hit.col + 1)
            if (tempPointUp in board && tempPointUp !in board.hits_space && tempPointUp !in board.misses) {
                return tempPointUp
            }
            if (tempPointDown in board && tempPointDown !in board.hits_space && tempPointDown !in board.misses) {
                return tempPointDown
            }
            if (tempPointLeft in board && tempPointLeft !in board.hits_space && tempPointLeft !in board.misses) {
                return tempPointLeft
            }
            if (tempPointRight in board && tempPointRight !in board.hits_space && tempPointRight !in board.misses) {
                return tempPointRight
            }
        }
        return Point(-1, -1)
    }

    private fun getSizeOfSunkShips(sunkShips: List<Int>): Int {
        var size = 0
        for (ship in sunkShips) {
            if (ship == 1) {
                size += 5
            }
            if (ship == 2) {
                size += 4
            }
            if (ship == 3 || ship == 4) {
                size += 3
            }
            if (ship == 5) {
                size += 2
            }
        }
        return size
    }

}