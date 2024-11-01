package com.example.solidprinciple

/**
 * https://www.youtube.com/playlist?app=desktop&list=PLK8IOvtbwVsuYW8KovGg9o6dlhspym8O_
 *
 * Liskov substitution principle:
 * 1. Derived class must be substitutable for their parent (base class). 
 * If S is subtype of T then objects of type T can be replaced with objects of type S.
 *
 * 2. Child should never narrow down the functionality of parent.
 * i.e. Object of subclass should be able to access the all the methods and properties of the superclass.
 *
 * It is same as real world(children are always replacing their parents)
 */

open class Vehicle {
    open var name : String = ""
    open fun startEngine() {
        println("Start engine of - ${this.name}")
    }
}

class Car : Vehicle() {
    override fun startEngine() {
        super.startEngine()
    }
}

class Cycle : Vehicle() {
    override fun startEngine() {
        throw EngineNotFoundException() //Child is narrowing down the functionality of parent. 
    }
}

class EngineNotFoundException : ClassNotFoundException()

class VehicleMonitor {
    fun startVehicles() {
        val vehicles = mutableListOf <Vehicle>()
        vehicles.add(Car())
        vehicles.add(Cycle())
        for (engine in vehicles) {
            engine.startEngine() // Problem Cycle will throw EngineNotFoundException
        }
    }
}


/**
 * Problem: We have not followed proper inheritance here that is the reason
 * Cycle can't be substituted for Vehicle
 */

/**
 * Solution: Proper inheritance should be taken place so that
 * Derived class must be substitutable for their parent.
 */


interface VehicleSolution {
    open val name : String
    fun startEngine() {
        println("Start engine of - ${this.name}")
    }

    fun start() {
        println("Start of - ${this.name}")
    }
}

abstract class VehicleWithEngine: VehicleSolution {
    override fun start() {
        startEngine()
    }
}

abstract class VehicleWithOutEngine: VehicleSolution {
    override fun start() {
        super.start()
    }
}

class CarSolution : VehicleWithEngine() {
    override val name : String = " Car"
    override fun start() {
        super.start()
    }
}

class CycleSolution : VehicleWithOutEngine() {
    override val name : String = " Cycle"
    override fun start() {
        super.start()
    }
}

//Flying vehicle can also be added

class VehicleMonitorSolution {
    fun startVehicles() {
        val vehicles = mutableListOf <VehicleSolution>()
        vehicles.add(CarSolution())
        vehicles.add(CycleSolution())
        for (engine in vehicles) {
            engine.start() // Solution: Cycle will start without EngineNotFoundException
        }
    }
}

fun main(args: Array<String>) {

    //VehicleMonitor().startVehicles() // Problem

    /**
     * Solution
     */
    VehicleMonitorSolution().startVehicles()
}


/**
 * It still have interface segeration problem
 */

