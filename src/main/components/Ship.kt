package main

data class Ship(
    val id: Int,
    val size: Int,
    val player: Int,
    val start: Point,
    val direction: Direction
) {
    val end: Point = start + direction * (size - 1)
    val ship_points: Set<Point> = getShipPoints(start, end)
    var hits = mutableSetOf<Point>()
    var sunk: Boolean = false

    operator fun contains(p: Point): Boolean {
        return when (direction) {
            Direction.HORIZONTAL -> start.row == p.row && start.col <= p.col && end.col >= p.col
            Direction.VERTICAL -> start.col == p.col && start.row <= p.row && end.row >= p.row
        }
    }

    operator fun contains(other: Ship): Boolean {
        if (other.direction == this.direction) {
            return other.start in this || other.end in this
        }
        val vertical = if (other.direction == Direction.VERTICAL) other else this
        val horizontal = if (other.direction == Direction.HORIZONTAL) other else this
        return horizontal.start.row in vertical.start.row..vertical.end.row
                && vertical.start.col in horizontal.start.col..horizontal.end.col
    }

    fun getShipPoints(start: Point, end: Point): Set<Point> {
        var ship_points_loop = mutableSetOf<Point>()
        ship_points_loop.add(start)
        ship_points_loop.add(end)
        if (size > 2) {
            for (x in 1 until size - 1) {
                ship_points_loop.add(start + direction * x)
            }
        }
        return ship_points_loop
    }

    fun refreshShipSunkness() {
        if (hits.size == size) {
            sunk = true
        }
    }
}
