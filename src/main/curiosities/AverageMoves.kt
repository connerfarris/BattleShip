package main.curiosities

import main.games.SinglePlayerGamePickClose
import main.games.SinglePlayerGamePickCloseSmartly
import main.games.SinglePlayerGamePickRandom

fun main() {
    val iterations = 100000
    val width = 10
    val height = 10

//    var moves = 0
//    for (x in 0..iterations) {
//        val game = SinglePlayerGamePickRandom(width, height)
//        game.playNoPrint()
//        moves += game.moveNum
//    }
//    println(moves / 1.0 / iterations)
//
//    var moves2 = 0
//    for (x in 0..iterations) {
//        val game = SinglePlayerGamePickClose(width, height)
//        game.playNoPrint()
//        moves2 += game.moveNum
//    }
//    println(moves2 / 1.0 / iterations)

    var moves3 = 0
    for (x in 0..iterations) {
        val game = SinglePlayerGamePickCloseSmartly(width, height)
        game.playNoPrint()
        moves3 += game.moveNum
    }
    println(moves3 / 1.0 / iterations)
}

//95.5 random
//66.6 pickclose
//61.6 pickclosesmartly
