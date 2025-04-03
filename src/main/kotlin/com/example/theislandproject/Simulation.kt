package com.example.theislandproject

import com.example.theislandproject.Animals.*
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.random.Random

class Simulation(private val params: SimulationParameters) {
    private val animals = mutableListOf<Animal>()
    private val plants = mutableListOf<Plant>()
    var isRunning = false
    var logs = StringBuilder()

    fun update() {
        if (isRunning) {
            spawnEntities()

            animals.forEach { animal ->
                if (animal.isAlive) {
                    animal.move()
                    animal.eat(animals.toTypedArray(), plants)
                    animal.reproduce()
                }
            }
        }
    }

    fun addAnimal(animal: Animal) {
        animals.add(animal)
        logs.append("${animal.type} added at position (${animal.x}, ${animal.y})\n")
    }

    fun addPlant(plant: Plant) {
        plants.add(plant)
    }

    private fun spawnEntities() {
        if (Random.nextDouble() < params.plantSpawnChance) {
            val plant = Plant(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight))
            plants.add(plant)
        }

        if (Random.nextDouble() < params.animalSpawnChance) {
            val animalType = listOf(
                "Wolf", "Fox", "Rabbit", "Deer", "Horse", "Goat", "Duck", "Buffalo", "Pig", "Caterpillar",
                "Sheep", "Mouse", "Bison", "Antelope", "Giraffe"
            ).random()
            when (animalType) {
                "Wolf" -> addAnimal(Wolf(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Fox" -> addAnimal(Fox(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Rabbit" -> addAnimal(Rabbit(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Deer" -> addAnimal(Deer(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Horse" -> addAnimal(Horse(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Goat" -> addAnimal(Goat(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Duck" -> addAnimal(Duck(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Buffalo" -> addAnimal(Buffalo(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Pig" -> addAnimal(Pig(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Caterpillar" -> addAnimal(Caterpillar(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Sheep" -> addAnimal(Sheep(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Mouse" -> addAnimal(Mouse(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Bison" -> addAnimal(Bison(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Antelope" -> addAnimal(Antelope(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
                "Giraffe" -> addAnimal(Giraffe(Random.nextInt(0, params.islandWidth), Random.nextInt(0, params.islandHeight)))
            }
        }
    }

    fun render(): Pane {
        val pane = Pane()

        animals.forEach { animal ->
            if (animal.isAlive) {
                val animalRepresentation = Circle(animal.x.toDouble(), animal.y.toDouble(), 10.0)
                animalRepresentation.fill = animal.getAnimalColor()
                pane.children.add(animalRepresentation)
            }
        }

        plants.forEach { plant ->
            val plantRepresentation = Circle(plant.x.toDouble(), plant.y.toDouble(), 5.0)
            plantRepresentation.fill = Color.BROWN
            pane.children.add(plantRepresentation)
        }

        return pane
    }

    fun getParams(): SimulationParameters {
        return params
    }

    fun getLogs(): String {
        return logs.toString()
    }
}