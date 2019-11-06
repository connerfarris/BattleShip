package main.logic

import main.components.Board
import main.components.Point
import java.lang.Math.abs
import java.util.*

class PickCloseStopWhenSunk(
        board: Board
) : LogicEngine(board) {

    var priorityPointMap = initPriorityPointMap()

    override fun move() {
        var movePoint: Point
        refreshPriority()
        movePoint = pickRandomPointInOrderOfPriority()
        return processMove(movePoint)
    }

    override fun moveVerbose() {
        var movePoint: Point
        println("1: " + priorityPointMap[1]?.size + "  2: " + priorityPointMap[2]?.size + "  3: " + priorityPointMap[3]?.size + "  4: " + priorityPointMap[4]?.size)
        refreshPriority()
        movePoint = pickRandomPointInOrderOfPriorityVerbose()
        return processMove(movePoint)
    }

    private fun refreshPriority() {
        var highPriority = mutableSetOf<Point>()
        var sunkShipPoints = mutableSetOf<Point>()
        for (shipId in board.sunkShips) {
            var ship = board.ships[shipId - 1]
            sunkShipPoints.addAll(ship.getShipPoints())
        }
        var hitsOnActiveShips = board.hitsSpace.filter { it !in sunkShipPoints }
        for (hit in hitsOnActiveShips) {
            val tempPointUp = Point(hit.row - 1, hit.col)
            val tempPointDown = Point(hit.row + 1, hit.col)
            val tempPointLeft = Point(hit.row, hit.col - 1)
            val tempPointRight = Point(hit.row, hit.col + 1)
            if (tempPointUp in board && tempPointUp !in board.hitsSpace && tempPointUp !in board.misses) {
                highPriority.add(tempPointUp)
            }
            if (tempPointDown in board && tempPointDown !in board.hitsSpace && tempPointDown !in board.misses) {
                highPriority.add(tempPointDown)
            }
            if (tempPointLeft in board && tempPointLeft !in board.hitsSpace && tempPointLeft !in board.misses) {
                highPriority.add(tempPointLeft)
            }
            if (tempPointRight in board && tempPointRight !in board.hitsSpace && tempPointRight !in board.misses) {
                highPriority.add(tempPointRight)
            }
        }
        priorityPointMap[2] = highPriority
        if (hitsOnActiveShips.size > 1) {
            putPointsInLineWithInLineHitsOnActiveShipsInHighestPriority(highPriority)
        }
    }

    private fun putPointsInLineWithInLineHitsOnActiveShipsInHighestPriority(highPriority: MutableSet<Point>) {
        var highestPriorityPoint = Point(-1, -1)
        var rowMap = mutableMapOf<Int, Int>()
        var colMap = mutableMapOf<Int, Int>()
        for (point in board.hitsSpace) {
            if (rowMap[point.row] == null) {
                rowMap[point.row] = 1
            } else {
                rowMap[point.row] = rowMap[point.row]!! + 1
            }
            if (colMap[point.col] == null) {
                colMap[point.col] = 1
            } else {
                colMap[point.col] = colMap[point.col]!! + 1
            }
        }
        for ((key, value) in rowMap) {
            if (value > 1) {
                for (point in highPriority) {
                    if (point.row.equals(key)) {
                        highestPriorityPoint = point
                        break
                    }
                }
            }
        }
        if (highestPriorityPoint != Point(-1, -1)) {
            priorityPointMap[1] = mutableSetOf(highestPriorityPoint)
            priorityPointMap[2]?.remove(highestPriorityPoint)
            return
        }
        for ((key, value) in colMap) {
            if (value > 1) {
                for (point in highPriority) {
                    if (point.col.equals(key)) {
                        highestPriorityPoint = point
                        break
                    }
                }
            }
        }
        if (highestPriorityPoint != Point(-1, -1)) {
            priorityPointMap[1] = mutableSetOf(highestPriorityPoint)
            priorityPointMap[2]?.remove(highestPriorityPoint)
            return
        }
    }

    private fun anyInLineHitsInHighPriority(highPriority: MutableSet<Point>): Boolean {
        if (highPriority.size <= 1) {
            return false
        }
        var rowList = mutableSetOf<Int>()
        var colList = mutableSetOf<Int>()
        for (point in highPriority) {
            rowList.add(point.row)
            colList.add(point.col)
        }
        if (rowList.size < highPriority.size || colList.size < highPriority.size) {
            return true
        }
        return false
    }

    private fun pickRandomPointInOrderOfPriority(): Point {
        for ((key, value) in priorityPointMap) {
            if (value.isEmpty()) {
                continue
            } else {
                val movePoint: Point = value.shuffled().take(1)[0]
                priorityPointMap[1]?.remove(movePoint)
                priorityPointMap[2]?.remove(movePoint)
                priorityPointMap[3]?.remove(movePoint)
                priorityPointMap[4]?.remove(movePoint)
                return movePoint
            }
        }
        return Point(-1, -1)
    }

    private fun pickRandomPointInOrderOfPriorityVerbose(): Point {
        for ((key, value) in priorityPointMap) {
            if (value.isEmpty()) {
                continue
            } else {
                val movePoint: Point = value.shuffled().take(1)[0]
                println("key: " + key)
                println("move: " + movePoint)
                priorityPointMap[1]?.remove(movePoint)
                priorityPointMap[2]?.remove(movePoint)
                priorityPointMap[3]?.remove(movePoint)
                priorityPointMap[4]?.remove(movePoint)
                return movePoint
            }
        }
        return Point(-1, -1)
    }

    private fun initPriorityPointMap(): SortedMap<Int, MutableSet<Point>> {
        var highest = mutableSetOf<Point>()
        var high = mutableSetOf<Point>()
        var even = mutableSetOf<Point>()
        var odd = mutableSetOf<Point>()
        var priorityMap = mutableMapOf<Int, MutableSet<Point>>()
        for (x in 0..board.height - 1) {
            for (y in 0..board.width - 1) {
                if (abs(x - y) % 2 == 0) {
                    even.add(Point(x, y))
                } else {
                    odd.add(Point(x, y))
                }
            }
        }
        priorityMap[1] = highest
        priorityMap[2] = high
        priorityMap[3] = odd
        priorityMap[4] = even
        return priorityMap.toSortedMap()
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