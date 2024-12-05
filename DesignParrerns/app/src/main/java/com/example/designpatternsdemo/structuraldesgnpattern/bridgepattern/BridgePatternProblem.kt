package com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern

/**
 * Bride pattern decouples an abstraction from it's implementation
 * so that two can vary independently.
 */

//Here we will show problem, which can be solved using Bridge pattern
//Synonyms: cavalryman, patrolman, state trooper, highway patrol, constable, deputy detective,
// marshal, military police officer, and policeman
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)

    //Problem: If we add new behaviour then all 5 class we have have to modify.
    //fun shout(): String
}

open class StormTrooper : Trooper {
    override fun move(x: Long, y: Long) {
        println("Move at normal speed 10km/hr")
    }

    override fun attackRebel(x: Long, y: Long) {
        println("Missed most of the time")
    }
}

open class ShockTrooper : Trooper {
    override fun move(x: Long, y: Long) {
        println("Move slower than normal speed i.e. 5km/hr")
    }

    override fun attackRebel(x: Long, y: Long) {
        println("Hits sometimes")
    }
}

//For Riot control, we want to develop RiotControlTrooper with Electric Baton
class RiotControlTrooper : StormTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        println("Trooper Has an electric Baton, stay away")
        super.attackRebel(x, y)
    }
}

class FlameTrooper : ShockTrooper() {
    override fun attackRebel(x: Long, y: Long) {
        println("Uses flame thrower, dangerous")
        super.attackRebel(x, y)
    }
}

class ScoutTrooper : ShockTrooper() {
    override fun move(x: Long, y: Long) {
        println("ScoutTrooper has sports bike and Move faster")
    }
}
//Problem: There is a new Weapon to attack, but we can't maintain that in our exiting system.
// To use that new weapon a new class has to implement the attackRebel method.
//This is because abstraction is tightly coupled with implementation.

private fun main() {

}
