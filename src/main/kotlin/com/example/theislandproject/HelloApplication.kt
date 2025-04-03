package com.example.theislandproject
import com.example.theislandproject.Animals.*
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.layout.HBox
import javafx.geometry.Pos
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.scene.control.TextField
import javafx.scene.control.Label
import javafx.application.Platform
class Main : Application() {
    private val simulation = Simulation(SimulationParameters())

    override fun start(primaryStage: Stage) {
        val root = VBox(20.0)
        root.alignment = Pos.TOP_CENTER

        val islandWidthField = TextField(simulation.getParams().islandWidth.toString())
        val islandHeightField = TextField(simulation.getParams().islandHeight.toString())
        val animalCountField = TextField(simulation.getParams().initialAnimalCount.toString())
        val plantCountField = TextField(simulation.getParams().initialPlantCount.toString())

        val widthLabel = Label("Island Width:")
        val heightLabel = Label("Island Height:")
        val animalCountLabel = Label("Initial Animal Count:")
        val plantCountLabel = Label("Initial Plant Count:")

        val startButton = Button("Start Simulation")
        val stopButton = Button("Stop Simulation")

        startButton.setOnAction {
            simulation.isRunning = true
        }

        stopButton.setOnAction {
            simulation.isRunning = false
        }

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

        val updateButton = Button("Update Parameters")
        updateButton.setOnAction {
            simulation.getParams().islandWidth = islandWidthField.text.toInt()
            simulation.getParams().islandHeight = islandHeightField.text.toInt()
            simulation.getParams().initialAnimalCount = animalCountField.text.toInt()
            simulation.getParams().initialPlantCount = plantCountField.text.toInt()
        }

        val logText = Text("Simulation Logs:")
        val logsDisplay = Text(simulation.getLogs())

        val controlPanel = HBox(10.0)
        controlPanel.alignment = Pos.CENTER
        controlPanel.children.addAll(
            addWolfButton, addFoxButton, addRabbitButton, addDeerButton, addHorseButton,
            addGoatButton, addDuckButton, addBuffaloButton, addPigButton, addCaterpillarButton,
            addBisonButton, addAntelopeButton, addGiraffeButton, addSheepButton, addMouseButton
        )

        val simulationArea = Pane()
        simulationArea.setPrefSize(600.0, 400.0)
        simulationArea.style = "-fx-background-color: #f0f0f0;"

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

        root.children.addAll(
            widthLabel, islandWidthField, heightLabel, islandHeightField,
            animalCountLabel, animalCountField, plantCountLabel, plantCountField,
            updateButton, controlPanel, startButton, stopButton, simulationArea, logText, logsDisplay
        )


        val scene = Scene(root, 800.0, 600.0)
        primaryStage.title = "Island Simulation"
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}