package main.components

data class Board(
        val id: Int,
        val player: Int,
        val width: Int = 10,
        val height: Int = 10
) {
    private val shipSizeList: List<Int> = determineShipsToCreate()
    private val tokenPoint: Point = Point(0, 0)
    private val tokenDirection: Direction = Direction.VERTICAL
    val ships: List<Ship> = autoPlaceShips(shipSizeList)
    val shipSpace: Map<Point, Int> = getShipSpace(ships)
    var hitsSpace = mutableSetOf<Point>()
    var misses = mutableSetOf<Point>()
    private var activeShips = ships.filter { !it.sunk }.map { it.id }
    var sunkShips = ships.filter { it.sunk }.map { it.id }
    var gameOver: Boolean = activeShips.isEmpty() && !ships.isEmpty()

    operator fun contains(p: Point): Boolean {
        return p.isValid() && p.row < height && p.col < width
    }

    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (hitsSpace.contains(Point(i, j))) {
                    print(" X")
                } else if (misses.contains(Point(i, j))) {
                    print(" o")
                } else if (shipSpace.contains(Point(i, j))) {
                    print(" -")
                } else {
                    print(" .")
                }
            }
            println()
        }
        println()
    }

    fun autoPlaceShips(ship_size_list: List<Int>): List<Ship> {
        val ship_list = mutableListOf<Ship>()
        var ship_id = 1
        for (ship_size in ship_size_list) {
            var iter_ship =
                    Ship(ship_id, ship_size, 1, tokenPoint.randomPoint(width, height), tokenDirection.randomDirection())
            while (!canShipBePlaced(iter_ship, ship_list)) {
                iter_ship =
                        Ship(ship_id, ship_size, 1, tokenPoint.randomPoint(width, height), tokenDirection.randomDirection())
            }
            ship_list.add(iter_ship)
            ship_id = ship_id + 1
        }
        return ship_list
    }

    fun canShipBePlaced(ship_to_place: Ship, ship_list: List<Ship>): Boolean {
        if (ship_list.isEmpty() && !isShipInBoard(ship_to_place)) {
            return false
        } else {
            for (ship in ship_list) {
                if (ship_to_place in ship || !isShipInBoard(ship_to_place)) {
                    return false
                }
            }
        }
        return true
    }

    fun isShipInBoard(ship_to_place: Ship): Boolean {
        if (ship_to_place.start.row >= height
                || ship_to_place.end.row >= height
                || ship_to_place.start.col >= width
                || ship_to_place.end.col >= width
        ) {
            return false
        }
        return true
    }

    fun getShipSpace(ships: List<Ship>): Map<Point, Int> {
        var ship_space_loop = mutableMapOf<Point, Int>()
        for (ship in ships) {
            ship_space_loop[ship.start] = ship.id
            for (x in (1 until ship.size)) {
                ship_space_loop[ship.start + ship.direction * x] = ship.id
            }
        }
        return ship_space_loop
    }

    fun refreshHitsFromShips() {
        var hits_space_loop = mutableSetOf<Point>()
        for (ship in ships) {
            hits_space_loop.addAll(ship.hits)
        }
        hitsSpace = hits_space_loop
    }

    fun refreshIndShipStates() {
        for (ship in ships) {
            ship.refreshShipSunkness()
        }
    }

    fun refreshBoardShipState() {
        sunkShips = ships.filter { it.sunk }.map { it.id }
        activeShips = ships.filter { !it.sunk }.map { it.id }
    }

    fun refreshGameOver() {
        gameOver = activeShips.isEmpty() && !ships.isEmpty()
    }

    fun refreshBoardState() {
        refreshHitsFromShips()
        refreshIndShipStates()
        refreshBoardShipState()
        refreshGameOver()
    }

    fun determineShipsToCreate(): List<Int> {
        var ship_size_list_loop = listOf<Int>()
        if (width > 0 && width <= 4 && height > 0 && height <= 4) {
            ship_size_list_loop = listOf(3, 2)
        } else if (width > 4 && width <= 6 && height > 4 && height <= 6) {
            ship_size_list_loop = listOf(3, 3, 2)
        } else if (width > 6 && width <= 8 && height > 6 && height <= 8) {
            ship_size_list_loop = listOf(4, 3, 3, 2)
        } else if (width > 8 && width <= 10 && height > 8 && height <= 10) {
            ship_size_list_loop = listOf(5, 4, 3, 3, 2)
        }
        return ship_size_list_loop
    }
}
