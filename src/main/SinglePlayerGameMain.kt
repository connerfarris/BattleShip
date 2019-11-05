package main

import main.games.SinglePlayerGamePickClose
import main.games.SinglePlayerGameRandom

fun main() {
    val width = 4
    val height = 4

//    val game = SinglePlayerGameRandom(width, height)
//    game.play()
//    println(game.moveNum.toString() + "/" + (width * height).toString())
//
//    val game2 = SinglePlayerGameRandom(width, height)
//    game2.playNoPrint()
//    println(game2.moveNum.toString() + "/" + (width * height).toString())

    val game3 = SinglePlayerGamePickClose(width, height)
    game3.play()
    println(game3.moveNum.toString() + "/" + (width * height).toString())
}
