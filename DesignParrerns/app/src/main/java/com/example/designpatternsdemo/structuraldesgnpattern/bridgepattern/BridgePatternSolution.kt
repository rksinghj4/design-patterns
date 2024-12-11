package com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern

typealias PointOfDamage = Long

typealias Meters = Int

const val RIFLE_DAMAGE: PointOfDamage = 4L
const val REGULAR_SPEED: Meters = 2

interface Trooper2 {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)

    fun displayDetails()
}

//Implementation is separated from abstraction, now both can grow independently

data class StormTrooper2(
    private val weapon: Weapon,
    private val vehicle: Vehicle
) : Trooper2 {
    override fun move(x: Long, y: Long) {
        vehicle.move(x, y)
    }

    override fun attackRebel(x: Long, y: Long) {
        weapon.attack(x, y)
    }

    override fun displayDetails() {
        println(this)
    }
}


// Now we can provide any implementation to Weapon and Vehicle
interface Weapon {
    fun attack(x: Long, y: Long): PointOfDamage
}

interface Vehicle {
    fun move(x: Long, y: Long): Meters
}

class Rifle : Weapon {
    override fun attack(x: Long, y: Long): PointOfDamage = RIFLE_DAMAGE
}

class FlameThrower : Weapon {
    override fun attack(x: Long, y: Long): PointOfDamage = RIFLE_DAMAGE * 2
}

class Bicycle : Vehicle {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED
}

class Bike : Vehicle {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED * 4
}

//Note:If classes don't have to preserve any state variables.
// We can reduce above classes by having Singleton and for each class method
// we can have one method in Singleton.


private fun main() {
    //Now on the fly we can pass any implementation of Weapon and vehicle
    val stormTrooperWithRifleAndBicycle = StormTrooper2(Rifle(), Bicycle())
    stormTrooperWithRifleAndBicycle.move(2L, 3L)
    stormTrooperWithRifleAndBicycle.attackRebel(2L, 3L)

    val stormTrooperWithFlameThrowerAndBike = StormTrooper2(FlameThrower(), Bike())
    stormTrooperWithFlameThrowerAndBike.move(4, 5)
    stormTrooperWithFlameThrowerAndBike.attackRebel(5, 6)
}