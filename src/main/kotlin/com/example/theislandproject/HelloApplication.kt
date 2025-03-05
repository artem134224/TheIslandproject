package com.example.theislandproject
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.scene.control.TextField
import javafx.scene.control.Label
import kotlin.random.Random
import javafx.application.Platform

// Класс для параметров симуляции
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

enum class StopCondition {
    ALL_ANIMALS_DEAD,
    TIME_LIMIT_REACHED
}

// Базовый класс для всех животных
abstract class Animal(var x: Int, var y: Int, val type: String, val color: Color) {
    var isAlive = true

    abstract fun move()
    abstract fun eat(animals: Array<Animal>, plants: MutableList<Plant>)
    abstract fun reproduce()
    abstract fun chooseDirection()
    abstract fun chanceToEat(): Double
    abstract fun getAnimalColor(): Color
}

// Хищники (6 видов)
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

class Snake(x: Int, y: Int) : Animal(x, y, "Snake", Color.GREEN) {
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
    override fun chanceToEat(): Double = 0.5
    override fun getAnimalColor(): Color = Color.GREEN
}

class Fox(x: Int, y: Int) : Animal(x, y, "Fox", Color.ORANGE) {
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
    override fun chanceToEat(): Double = 0.5
    override fun getAnimalColor(): Color = Color.ORANGE
}

class Bear(x: Int, y: Int) : Animal(x, y, "Bear", Color.BROWN) {
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
    override fun chanceToEat(): Double = 0.4
    override fun getAnimalColor(): Color = Color.BROWN
}

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

// Новые хищники
class Boa(x: Int, y: Int) : Animal(x, y, "Boa", Color.DARKGREEN) {
    override fun move() {}
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
    override fun chanceToEat(): Double = 0.4
    override fun getAnimalColor(): Color = Color.DARKGREEN
}

// Травоядные (9 видов)
class Rabbit(x: Int, y: Int) : Animal(x, y, "Rabbit", Color.PINK) {
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
    override fun chanceToEat(): Double = 0.1
    override fun getAnimalColor(): Color = Color.PINK
}

class Deer(x: Int, y: Int) : Animal(x, y, "Deer", Color.BLUE) {
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
    override fun getAnimalColor(): Color = Color.BLUE
}

class Horse(x: Int, y: Int) : Animal(x, y, "Horse", Color.RED) {
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
    override fun chanceToEat(): Double = 0.3
    override fun getAnimalColor(): Color = Color.RED
}

class Goat(x: Int, y: Int) : Animal(x, y, "Goat", Color.LIME) {
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
    override fun chanceToEat(): Double = 0.4
    override fun getAnimalColor(): Color = Color.LIME
}

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

class Buffalo(x: Int, y: Int) : Animal(x, y, "Buffalo", Color.BLACK) {
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
    override fun chanceToEat(): Double = 0.1
    override fun getAnimalColor(): Color = Color.BLACK
}

class Pig(x: Int, y: Int) : Animal(x, y, "Pig", Color.VIOLET) {
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
    override fun chanceToEat(): Double = 0.3
    override fun getAnimalColor(): Color = Color.VIOLET
}

// Дополнительные травоядные
class Bison(x: Int, y: Int) : Animal(x, y, "Bison", Color.DARKBLUE) {
    override fun move() { chooseDirection() }
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
    override fun chanceToEat(): Double = 0.4
    override fun getAnimalColor(): Color = Color.DARKBLUE
}

class Antelope(x: Int, y: Int) : Animal(x, y, "Antelope", Color.YELLOWGREEN) {
    override fun move() { chooseDirection() }
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
    override fun chanceToEat(): Double = 0.5
    override fun getAnimalColor(): Color = Color.YELLOWGREEN
}
// Гусеница
class Caterpillar(x: Int, y: Int) : Animal(x, y, "Caterpillar", Color.YELLOW) {
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

    override fun chanceToEat(): Double = 0.1
    override fun getAnimalColor(): Color = Color.YELLOW
}

// Овца
class Sheep(x: Int, y: Int) : Animal(x, y, "Sheep", Color.WHITE) {
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

    override fun chanceToEat(): Double = 0.4
    override fun getAnimalColor(): Color = Color.WHITE
}

// Мышь
class Mouse(x: Int, y: Int) : Animal(x, y, "Mouse", Color.GRAY) {
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
    override fun getAnimalColor(): Color = Color.GRAY
}

class Giraffe(x: Int, y: Int) : Animal(x, y, "Giraffe", Color.GOLD) {
    override fun move() { chooseDirection() }
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
    override fun chanceToEat(): Double = 0.6
    override fun getAnimalColor(): Color = Color.GOLD
}

class Plant(var x: Int, var y: Int)

// Класс Симуляции
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
class Main : Application() {
    private val simulation = Simulation(SimulationParameters())

    override fun start(primaryStage: Stage) {
        val root = VBox(20.0)
        root.alignment = Pos.TOP_CENTER

        // Поля для ввода параметров острова
        val islandWidthField = TextField(simulation.getParams().islandWidth.toString())
        val islandHeightField = TextField(simulation.getParams().islandHeight.toString())
        val animalCountField = TextField(simulation.getParams().initialAnimalCount.toString())
        val plantCountField = TextField(simulation.getParams().initialPlantCount.toString())

        // Метки для полей ввода
        val widthLabel = Label("Island Width:")
        val heightLabel = Label("Island Height:")
        val animalCountLabel = Label("Initial Animal Count:")
        val plantCountLabel = Label("Initial Plant Count:")

        // Кнопки для запуска и остановки симуляции
        val startButton = Button("Start Simulation")
        val stopButton = Button("Stop Simulation")

        startButton.setOnAction {
            simulation.isRunning = true
        }

        stopButton.setOnAction {
            simulation.isRunning = false
        }

        // Кнопки для добавления животных
        val addWolfButton = Button("Add Wolf")
        addWolfButton.setOnAction {
            simulation.addAnimal(Wolf(100, 100))
        }

        val addFoxButton = Button("Add Fox")
        addFoxButton.setOnAction {
            simulation.addAnimal(Fox(150, 150))
        }

        val addRabbitButton = Button("Add Rabbit")
        addRabbitButton.setOnAction {
            simulation.addAnimal(Rabbit(250, 250))
        }

        val addDeerButton = Button("Add Deer")
        addDeerButton.setOnAction {
            simulation.addAnimal(Deer(300, 300))
        }

        val addHorseButton = Button("Add Horse")
        addHorseButton.setOnAction {
            simulation.addAnimal(Horse(350, 350))
        }

        val addGoatButton = Button("Add Goat")
        addGoatButton.setOnAction {
            simulation.addAnimal(Goat(400, 400))
        }

        val addDuckButton = Button("Add Duck")
        addDuckButton.setOnAction {
            simulation.addAnimal(Duck(450, 450))
        }

        val addBuffaloButton = Button("Add Buffalo")
        addBuffaloButton.setOnAction {
            simulation.addAnimal(Buffalo(500, 500))
        }

        val addPigButton = Button("Add Pig")
        addPigButton.setOnAction {
            simulation.addAnimal(Pig(550, 550))
        }

        val addCaterpillarButton = Button("Add Caterpillar")
        addCaterpillarButton.setOnAction {
            simulation.addAnimal(Caterpillar(600, 600))
        }

        val addBisonButton = Button("Add Bison")
        addBisonButton.setOnAction {
            simulation.addAnimal(Bison(650, 650))
        }

        val addAntelopeButton = Button("Add Antelope")
        addAntelopeButton.setOnAction {
            simulation.addAnimal(Antelope(700, 700))
        }

        val addGiraffeButton = Button("Add Giraffe")
        addGiraffeButton.setOnAction {
            simulation.addAnimal(Giraffe(750, 750))
        }

        val addSheepButton = Button("Add Sheep")
        addSheepButton.setOnAction {
            simulation.addAnimal(Sheep(800, 800))
        }

        val addMouseButton = Button("Add Mouse")
        addMouseButton.setOnAction {
            simulation.addAnimal(Mouse(850, 850))
        }

        // Обновление параметров симуляции
        val updateButton = Button("Update Parameters")
        updateButton.setOnAction {
            simulation.getParams().islandWidth = islandWidthField.text.toInt()
            simulation.getParams().islandHeight = islandHeightField.text.toInt()
            simulation.getParams().initialAnimalCount = animalCountField.text.toInt()
            simulation.getParams().initialPlantCount = plantCountField.text.toInt()
        }

        // Лог текста
        val logText = Text("Simulation Logs:")
        val logsDisplay = Text(simulation.getLogs())

        // Создание панели для кнопок
        val controlPanel = HBox(10.0)
        controlPanel.alignment = Pos.CENTER
        controlPanel.children.addAll(
            addWolfButton, addFoxButton, addRabbitButton, addDeerButton, addHorseButton,
            addGoatButton, addDuckButton, addBuffaloButton, addPigButton, addCaterpillarButton,
            addBisonButton, addAntelopeButton, addGiraffeButton, addSheepButton, addMouseButton
        )

        // Панель для отображения острова
        val simulationArea = Pane()
        simulationArea.setPrefSize(600.0, 400.0)
        simulationArea.style = "-fx-background-color: #f0f0f0;"

        // Таймер обновления симуляции
        val timer = object : Thread() {
            override fun run() {
                while (true) {
                    simulation.update()
                    Platform.runLater {
                        val pane = simulation.render()
                        simulationArea.children.clear()
                        simulationArea.children.add(pane)
                        logsDisplay.text = simulation.getLogs()
                    }
                    Thread.sleep(simulation.getParams().simulationTickDuration)
                }
            }
        }
        timer.start()

        // Размещение всех компонентов на сцене
        root.children.addAll(
            widthLabel, islandWidthField, heightLabel, islandHeightField,
            animalCountLabel, animalCountField, plantCountLabel, plantCountField,
            updateButton, controlPanel, startButton, stopButton, simulationArea, logText, logsDisplay
        )

        // Сцена и её отображение
        val scene = Scene(root, 800.0, 600.0)
        primaryStage.title = "Island Simulation"
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}

