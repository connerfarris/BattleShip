package main

data class TestDataClass (val x: Int, var y: Int) {
    var z: Int = x * y

    fun reGetZ() {
        z = x * y
    }
}