package main.curiosities

import main.games.SinglePlayerGame

fun main() {
    val iterations = 10000
    val width = 10
    val height = 10

    var moves = 0
    for (x in 0..iterations) {
        val game = SinglePlayerGame(width, height, "random")
        game.playNoPrint()
        moves += game.moveNum
    }
    println(moves / 1.0 / iterations)

    var moves2 = 0
    for (x in 0..iterations) {
        val game = SinglePlayerGame(width, height, "pickClose")
        game.playNoPrint()
        moves2 += game.moveNum
    }
    println(moves2 / 1.0 / iterations)

    var moves3 = 0
    for (x in 0..iterations) {
        val game = SinglePlayerGame(width, height, "pickCloseSmartly")
        game.playNoPrint()
        moves3 += game.moveNum
    }
    println(moves3 / 1.0 / iterations)
}

//for 10x10 board:
//95.5 random
//66.6 pickclose
//61.6 pickclosesmartly
