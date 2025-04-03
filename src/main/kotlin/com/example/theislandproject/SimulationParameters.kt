package com.example.theislandproject

class SimulationParameters {
    var islandWidth: Int = 100
    var islandHeight: Int = 100
    var maxAnimalsPerCell: Int = 1
    var simulationTickDuration: Long = 500L
    var initialAnimalCount: Int = 10
    var initialPlantCount: Int = 10
    var maxOffspring: Int = 3
    var stopCondition: StopCondition = StopCondition.ALL_ANIMALS_DEAD
    var plantSpawnChance: Double = 0.1
    var animalSpawnChance: Double = 0.1
}