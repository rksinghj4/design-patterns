package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bicycle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Bike
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.FlameThrower
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Rifle
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.StormTrooper2
import com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern.Trooper2

/**
 * The Composite Pattern lets you treat individual objects and groups of objects in the same way.
 *
 * It's commonly used when you have a tree structure — like a file system, UI layout, or organization hierarchy.
 *
 * Whenever we encountered a problem in tree like structure(Inheritance) mostly
 * it can be solved using composite pattern.
 *
 * Component (interface)
 *  ├── Leaf (single item)
 *  └── Composite (group of components)
 *
 */

//If we add new method to Trooper2 interface then Squad class will force us to override that method
//Now squad is easily scalable and flexible.
data class Squad(private val units: List<Trooper2>, private var index: Int = 0) : Trooper2 {


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

    override fun displayDetails() {
        val iterator: Iterator<Trooper2?> = iterator()
        while (iterator.hasNext()) {
            /*
            when (val item = iterator.next()) {
                is Squad -> item.printElements(item.iterator())// is Composite object
                is StormTrooper2 -> println(item) // is Leaf object
            }
             */
            val item = iterator.next() as? Trooper2
            item?.displayDetails()
        }
    }

    //Here anonymous class is implementing kotlin's Iterator
    operator fun iterator() = object : Iterator<Trooper2?> {
        //Using Kotlin's Iterator interface
        override fun hasNext(): Boolean {
            return index < units.size
        }

        override fun next(): Trooper2? {
            if (hasNext()) {
                return units[index++]
            }
            return null
        }
    }

    fun<T> printElements(iterator: Iterator<T>? = null) {

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