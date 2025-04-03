package com.example.theislandproject

import javafx.scene.paint.Color

abstract class Animal(var x: Int, var y: Int, val type: String, val color: Color) {
    var isAlive = true

    abstract fun move()
    abstract fun eat(animals: Array<Animal>, plants: MutableList<Plant>)
    abstract fun reproduce()
    abstract fun chooseDirection()
    abstract fun chanceToEat(): Double
    abstract fun getAnimalColor(): Color
}