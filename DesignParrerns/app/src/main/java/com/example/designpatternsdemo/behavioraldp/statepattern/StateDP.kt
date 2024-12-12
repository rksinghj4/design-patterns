package com.example.designpatternsdemo.behavioraldp.statepattern

/**
 * State DP: It is behavioural dp that allows an object to change its behavior
 * when its internal state changes. This pattern is particularly useful when an object’s behavior
 * depends on its state, and the state can change during the object’s lifecycle.
 *
 * This pattern focuses on managing state transitions and coordinating state-specific behaviors.
 *
 */

interface WhatCanHappened {
    fun seeHero()
    fun getHit(pointOfDamage: Int)
    fun calmAgain()
}

//State
sealed interface Mood {
    fun actAsPerTheMood()
}

//Concrete states
data object Still : Mood {
    override fun actAsPerTheMood() {
        println("Snail is standing Still to conserve the energy")
    }
}

data object Aggressive : Mood {
    override fun actAsPerTheMood() {
        println("Snail is dashing towards hero Aggressively")
    }
}

data object Retreating : Mood {
    override fun actAsPerTheMood() {
        println("Snail is Retreating to lick his wound")
    }
}

data object Dead : Mood {
    override fun actAsPerTheMood() {
        println("Snail is dead")
    }
}


//Context - An object whose behaviour changes based on the internal state change
class Snail : WhatCanHappened {
    internal var mood: Mood =
        Still// internal so that mood can be changes from other files in same module
    private var healthPoint = 10

    init {
        action()
    }

    override fun seeHero() {
        mood = when (mood) {
            is Still -> Aggressive
            else -> mood
        }
    }

    override fun getHit(pointOfDamage: Int) {
        healthPoint -= pointOfDamage
        mood = when {
            (healthPoint <= 0) -> Dead
            mood is Aggressive -> Retreating
            else -> mood
        }
    }

    override fun calmAgain() {
        mood = when (mood) {
            !is Dead -> Still
            else -> mood
        }
    }

    fun action() {//Context object is delegating the request to appropriate State object
        mood.actAsPerTheMood()
    }
}

//Client is interacting with Context object, whose behaviour changes based on state changes.
private fun main() {
    val snail = Snail()
    snail.seeHero()
    snail.action()

    snail.getHit(5)
    snail.action()

    snail.calmAgain()
    snail.action()

    snail.seeHero()
    snail.action()

    snail.getHit(5)
    snail.action()

    snail.calmAgain()//Note: here Context object has discarded the client's new input
    snail.action()

}