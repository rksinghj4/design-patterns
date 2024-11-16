package com.example.designpatternsdemo.creationaldesignpattern.builderpattern

/**
 * The Product is the complex object that the Builder pattern is responsible for constructing.
 *
 */
//1. Product
data class PersonalComputer(
    var processor: String = "",
    var memory: Int = 0,
    var storage: Int = 0,
    var graphicsCard: String = ""
)

//2. Builder interface/abstract class
interface Builder {
    //Each method declaration represents construction step
    fun buildProcessor(processor: String): Builder
    fun buildMemory(memory: Int): Builder
    fun buildStorage(storage: Int): Builder
    fun graphicsCard(graphicsCard: String): Builder
    fun build(): PersonalComputer
}

//3. Concrete builder like AppleMacBookBuilder, DellLaptopBuilder are different variations of personal computers etc
class AppleMacBookBuilder : Builder {

    private var personalComputer: PersonalComputer = PersonalComputer()

    //Concrete builder implements each construction step in its own way
    override fun buildProcessor(processor: String): Builder {
        personalComputer.processor = processor
        return this
    }

    override fun buildMemory(memory: Int): Builder {
        personalComputer.memory = memory
        return this
    }

    override fun buildStorage(storage: Int): Builder {
        personalComputer.storage = storage
        return this
    }

    override fun graphicsCard(graphicsCard: String): Builder {
        personalComputer.graphicsCard = graphicsCard
        return this
    }

    override fun build(): PersonalComputer = personalComputer
}


//4. Director (optional)
//The Director is responsible for managing the construction process of the complex object.
class ComputerDirector(private val builder: Builder) {
    //it doesn’t know the specific details about how each part of the object is constructed.
    fun constructPersonalComputer() = builder.buildProcessor("14-core CPU, Apple M2 Ultra")
        .buildMemory(36)
        .buildStorage(500)
        .graphicsCard("xyz")
        .build()
}

//Client
private fun main() {
    val appleMacBookBuilder = AppleMacBookBuilder()
    val computerDirector = ComputerDirector(appleMacBookBuilder)
    val personalComputer = computerDirector.constructPersonalComputer()
    println(personalComputer)
}

