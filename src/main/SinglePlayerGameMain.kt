package main

import main.games.SinglePlayerGame

fun main() {
    val width = 10
    val height = 10

//    val game = SinglePlayerGame(width, height, "random")
//    game.playVerbose()
//    println(game.moveNum.toString() + "/" + (width * height).toString())
//
//    val game2 = SinglePlayerGame(width, height, "random")
//    game2.playNoPrint()
//    println(game2.moveNum.toString() + "/" + (width * height).toString())
//
//    val game3 = SinglePlayerGame(width, height, "pickClose")
//    game3.play()
//    println(game3.moveNum.toString() + "/" + (width * height).toString())

//    val game4 = SinglePlayerGame(width, height, "pickCloseSmartly")
//    game4.play()
//    println(game4.moveNum.toString() + "/" + (width * height).toString())

    val game5 = SinglePlayerGame(width, height, "pickCloseStopWhenSunk")
    game5.playVerbose()
    println(game5.moveNum.toString() + "/" + (width * height).toString())
}
