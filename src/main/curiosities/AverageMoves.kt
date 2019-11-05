package main.curiosities

import main.games.SinglePlayerGame

fun main() {
    val iterations = 1000000
    val width = 10
    val height = 10

//    var moves = 0
//    for (x in 0..iterations) {
//        val game = SinglePlayerGame(width, height, "random")
//        game.playNoPrint()
//        moves += game.moveNum
//    }
//    println(moves / 1.0 / iterations)
//
//    var moves2 = 0
//    for (x in 0..iterations) {
//        val game = SinglePlayerGame(width, height, "pickClose")
//        game.playNoPrint()
//        moves2 += game.moveNum
//    }
//    println(moves2 / 1.0 / iterations)

//    var moves3 = 0
//    for (x in 0..iterations) {
//        val game = SinglePlayerGame(width, height, "pickCloseSmartly")
//        game.playNoPrint()
//        moves3 += game.moveNum
//    }
//    println(moves3 / 1.0 / iterations)

    var moves4 = 0
    for (x in 0..iterations) {
        val game = SinglePlayerGame(width, height, "pickCloseStopWhenSunk")
        game.playNoPrint()
//        println(moves4)
        moves4 += game.moveNum
    }
    println(moves4 / 1.0 / iterations)
}

//for 10x10 board:
//95.41097 random
//66.61893 pickclose
//61.64198 pickclosesmartly
//61.60888 pickclosestopwhensunk
