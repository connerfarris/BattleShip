package main

fun main() {
    val width = 4
    val height = 4
    val game = SinglePlayerGame(width,height)
    game.play()
    println(game.moveNum.toString() + "/" + (width * height).toString())
}
