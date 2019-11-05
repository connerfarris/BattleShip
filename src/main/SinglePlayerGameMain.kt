package main

import main.games.SinglePlayerGamePickClose
import main.games.SinglePlayerGamePickCloseSmartly
import main.games.SinglePlayerGamePickRandom

fun main() {
    val width = 10
    val height = 10

//    val game = SinglePlayerGameRandom(width, height)
//    game.play()
//    println(game.moveNum.toString() + "/" + (width * height).toString())

//    val game2 = SinglePlayerGameRandom(width, height)
//    game2.playNoPrint()
//    println(game2.moveNum.toString() + "/" + (width * height).toString())

//    val game3 = SinglePlayerGamePickClose(width, height)
//    game3.play()
//    println(game3.moveNum.toString() + "/" + (width * height).toString())

    val game4 = SinglePlayerGamePickCloseSmartly(width, height)
    game4.play()
    println(game4.moveNum.toString() + "/" + (width * height).toString())
}
