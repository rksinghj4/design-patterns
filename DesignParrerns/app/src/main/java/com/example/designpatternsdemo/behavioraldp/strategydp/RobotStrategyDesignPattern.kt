package com.example.designpatternsdemo.behavioraldp.strategydp

//Define a interface for changing behaviour
interface Walkable {
    fun walk()
}

class SlowWalk : Walkable {
    override fun walk() {
        println("SlowWalk")
    }
}

class FastWalk : Walkable {
    override fun walk() {
        println("FastWalk..")
    }
}

interface Talkable {
    fun talk()
}

class HindiTalk : Talkable {
    override fun talk() {
        println("Hindi Talk")
    }
}

class EnglishTalk : Talkable {
    override fun talk() {
        println("English Talk")
    }
}


abstract class DynamicRobot(walkable: Walkable, talkable: Talkable) : Walkable by walkable,
    Talkable by talkable {
    abstract fun projection()
}

class HouseHelpRobot(walkable: Walkable, talkable: Talkable) : DynamicRobot(walkable, talkable) {
    override fun projection() {
        println("Friendly HouseHelpRobot")
    }
}

class FactoryAssistantRobot(walkable: Walkable, talkable: Talkable) :
    DynamicRobot(walkable, talkable) {
    override fun projection() {
        println("Friendly FactoryAssistant Robot")
    }
}

private fun main() {
    val slowWalk = SlowWalk()
    val fastWalk = FastWalk()
    val hindiTalk = HindiTalk()
    val englishTalk = EnglishTalk()

    val houseHelpRobot = HouseHelpRobot(slowWalk, hindiTalk)
    houseHelpRobot.walk()
    houseHelpRobot.talk()
    houseHelpRobot.projection()

    println("_____________________________________________")

    val factoryAssistantRobot = FactoryAssistantRobot(fastWalk, englishTalk)
    factoryAssistantRobot.walk()
    factoryAssistantRobot.talk()
    factoryAssistantRobot.projection()
}


