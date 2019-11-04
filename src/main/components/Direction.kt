package main.components

enum class Direction {
    HORIZONTAL, VERTICAL;

    operator fun times(n: Int): WeightedDirection {
        return WeightedDirection(n, this)
    }

    fun randomDirection(): Direction {
        val rand = (0..1).random()
        if (rand == 1) {
            return HORIZONTAL
        }
        return VERTICAL
    }
}
