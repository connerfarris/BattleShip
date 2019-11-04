package main

import main.games.SinglePlayerGame

fun main() {
    val width = 4
    val height = 4
    val game = SinglePlayerGame(width, height)
    game.play()
    println(game.moveNum.toString() + "/" + (width * height).toString())

    val width2 = 4
    val height2 = 4
    val game2 = SinglePlayerGame(width2, height2)
    game2.playNoPrint()
    println(game2.moveNum.toString() + "/" + (width2 * height2).toString())
}
