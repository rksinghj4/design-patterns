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
    //executes the behavior associated with that state
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
    // executes the behavior associated with that state
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

    //Based on WhatCanHappened interface state management is happening inside context
    override fun seeHero() {//Changing the state
        mood = when (mood) {
            is Still -> Aggressive
            else -> mood
        }
    }

    override fun getHit(pointOfDamage: Int) {
        healthPoint -= pointOfDamage
        mood = when {
            (healthPoint <= 0) -> Dead//Smooth state transitions
            mood is Aggressive -> Retreating//Smooth state transitions
            else -> mood
        }
    }

    override fun calmAgain() {
        mood = when (mood) {
            !is Dead -> Still//Smooth state transitions
            else -> mood
        }
    }

    //Context object is delegating the action/request to appropriate State object
    fun action() {
        //Context class is unaware of how the task/behaviour has been implemented inside concrete State
        //Current state object -  executes the behavior associated with that state
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