package main.curiosities

import main.components.Board
import main.components.Point

fun main() {
    val how_far: Int = 100000
    var ship_space_set = mutableSetOf<Set<Point>>()
    for (x in 0..how_far) {
        var board = Board(1, 1, 5, 5)
        ship_space_set.add(board.ship_space.keys)
        println(x)
    }
    println("\nhow many orientations of 3 ships in 5x5? \n" + ship_space_set.size.toString())


    //    for (x in 4..10) {
    //        val board = Board(1,1,x,x)
    //        println(board.ships.size)
    //        board.printBoard() 37366
    //    }
//    248 for 4x4
//    7192 for 5x5
//    37366 for 6x6
//    866426 for 7x7

}