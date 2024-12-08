package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bicycle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bike
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.FlameThrower
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Rifle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.StormTrooper2
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Trooper2

/**
 * Composite patterns empowers us to support a tree-like structure of diverse complexity,
 * allowing us to execute operations on all nodes with in tree.
 *
 * Whenever we encountered a problem in tree like structure mostly
 * it can be solved using composite pattern.
 *
 * Composite pattern help us to treat the individual object in the same way as the collection of
 * those individual objects.
 *
 *
 */

//If we add new method to Trooper2 interface then Squad class will force us to override that method
//Now squad is easily scalable and flexible.
class Squad(private val units: List<Trooper2>) : Trooper2 {
    constructor(vararg units: Trooper2) : this(units.toList())

    override fun move(x: Long, y: Long) {
        println("Squad is moving")
        units.forEach {
            it.move(x, y)
        }
    }

    override fun attackRebel(x: Long, y: Long) {
        println("Squad is attacking rebel")
        units.forEach {
            it.attackRebel(x, y)
        }
    }
}

private fun main() {
    val stormTrooperWithRifleAndBicycle = StormTrooper2(Rifle(), Bicycle())
    val squad1 = Squad(
        stormTrooperWithRifleAndBicycle,
        stormTrooperWithRifleAndBicycle.copy(),
        stormTrooperWithRifleAndBicycle.copy()
    )

    stormTrooperWithRifleAndBicycle.move(2L, 3L)
    stormTrooperWithRifleAndBicycle.attackRebel(2L, 3L)
    squad1.move(2L, 3L)
    //Collection of individual objects can execute same operations as individual objects
    squad1.attackRebel(2L, 3L)

    val stormTrooperWithFlameThrowerAndBike = StormTrooper2(FlameThrower(), Bike())
    val squad2 = Squad(
        stormTrooperWithFlameThrowerAndBike,
        stormTrooperWithFlameThrowerAndBike.copy(),
        stormTrooperWithFlameThrowerAndBike.copy()
    )

    stormTrooperWithFlameThrowerAndBike.move(4, 5)
    stormTrooperWithFlameThrowerAndBike.attackRebel(5, 6)

    squad2.move(4, 5)
    squad2.attackRebel(5, 6)
}