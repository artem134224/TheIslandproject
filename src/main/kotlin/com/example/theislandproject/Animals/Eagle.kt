package com.example.theislandproject.Animals

import com.example.theislandproject.Animal
import com.example.theislandproject.Plant
import javafx.scene.paint.Color
import kotlin.random.Random

class Eagle(x: Int, y: Int) : Animal(x, y, "Eagle", Color.YELLOW) {
    override fun move() {
        chooseDirection()
    }
    override fun eat(animals: Array<Animal>, plants: MutableList<Plant>) {
        for (animal in animals) {
            if (animal is Rabbit && this.x == animal.x && this.y == animal.y) {
                animal.isAlive = false
            }
        }
    }
    override fun reproduce() {}
    override fun chooseDirection() {
        x += Random.nextInt(-1, 2)
        y += Random.nextInt(-1, 2)
    }
    override fun chanceToEat(): Double = 0.7
    override fun getAnimalColor(): Color = Color.YELLOW
}