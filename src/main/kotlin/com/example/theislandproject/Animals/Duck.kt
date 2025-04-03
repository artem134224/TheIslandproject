package com.example.theislandproject.Animals

import com.example.theislandproject.Animal
import com.example.theislandproject.Plant
import javafx.scene.paint.Color
import kotlin.random.Random

class Duck(x: Int, y: Int) : Animal(x, y, "Duck", Color.AQUA) {
    override fun move() {
        chooseDirection()
    }
    override fun eat(animals: Array<Animal>, plants: MutableList<Plant>) {
        for (plant in plants) {
            if (this.x == plant.x && this.y == plant.y) {
                plants.remove(plant)
                break
            }
        }
    }
    override fun reproduce() {}
    override fun chooseDirection() {
        x += Random.nextInt(-1, 2)
        y += Random.nextInt(-1, 2)
    }
    override fun chanceToEat(): Double = 0.2
    override fun getAnimalColor(): Color = Color.AQUA
}