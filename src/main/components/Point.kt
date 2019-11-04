package main

data class Point(
    val row: Int,
    val col: Int
) {
    fun isValid() = col >= 0 && row >= 0

    operator fun plus(wd: WeightedDirection): Point {
        return when (wd.dir) {
            Direction.HORIZONTAL -> Point(row, col + wd.len)
            Direction.VERTICAL -> Point(row + wd.len, col)
        }
    }

    fun randomPoint(x: Int, y: Int): Point {
        val rand_x = (0 until x).random()
        val rand_y = (0 until y).random()
        return Point(rand_x, rand_y)
    }
}
