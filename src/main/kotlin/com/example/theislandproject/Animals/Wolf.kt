package com.example.theislandproject.Animals

import com.example.theislandproject.Animal
import com.example.theislandproject.Plant
import javafx.scene.paint.Color

class Wolf(x: Int, y: Int) : Animal(x, y, "Wolf", Color.GRAY) {
    override fun move() {}
    override fun eat(animals: Array<Animal>, plants: MutableList<Plant>) {
        for (animal in animals) {
            if (animal is Rabbit && this.x == animal.x && this.y == animal.y) {
                animal.isAlive = false
            }
        }
    }
    override fun reproduce() {}
    override fun chooseDirection() {}
    override fun chanceToEat(): Double = 0.6
    override fun getAnimalColor(): Color = Color.GRAY
}