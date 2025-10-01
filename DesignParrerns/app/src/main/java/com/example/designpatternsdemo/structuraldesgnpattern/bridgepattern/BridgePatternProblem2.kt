package com.example.designpatternsdemo.structuraldesgnpattern.bridgepattern


//Here we will show problem, which can be solved using Bridge pattern
interface Army {
    fun move(x: Long, y: Long)
    fun attackEnemy(x: Long, y: Long)
}

class RoadPetrolArmy: Army {
    override fun move(x: Long, y: Long) {
        println("RoadPetrolArmy moving to coordinates: ($x, $y) at walking speed 5km/hr")
    }
    override fun attackEnemy(x: Long, y: Long) {
        println("RoadPetrolArmy attacking enemy at coordinates: ($x, $y) with a Batten")
    }
}

class IndianArmy: Army {
    override fun move(x: Long, y: Long) {
        println("IndianArmy moving to coordinates: ($x, $y) at speed 20km/hr")
    }
    override fun attackEnemy(x: Long, y: Long) {
        println("IndianArmy attacking enemy at coordinates: ($x, $y) with a Gun")
    }
}

class IndianAirForce: Army {
    override fun move(x: Long, y: Long) {
        println("IndianAirForce moving to coordinates: ($x, $y) at speed 2000km/hr")
    }
    override fun attackEnemy(x: Long, y: Long) {
        println("IndianAirForce attacking enemy at coordinates: ($x, $y) with a Missile")
    }
}

//Here the problem is if we want to have new weapon or vehicle then we have to create a new class everytime.

/**
* Bride pattern decouples an abstraction from it's implementation
* so that two can vary independently.
*/

class Missile : Weapon {
    override fun attack(x: Long, y: Long): PointOfDamage = 10L
}

class Gun : Weapon {
    override fun attack(x: Long, y: Long): PointOfDamage = 5L
}

class Batten : Weapon {
    override fun attack(x: Long, y: Long): PointOfDamage = 1L
}

class Jeep : Vehicle {
    override fun move(x: Long, y: Long): Meters = 20
}

class Truck : Vehicle {
    override fun move(x: Long, y: Long): Meters = 10
}

class FighterJet : Vehicle {
    override fun move(x: Long, y: Long): Meters = 2000
}
//Solution using Bridge Pattern
//Now we can provide any implementation to Weapon and Vehicle

class CustomArmy(private val weapon: Weapon, private val vehicle: Vehicle) : Army {
    override fun move(x: Long, y: Long) {
        vehicle.move(x, y)
    }

    override fun attackEnemy(x: Long, y: Long) {
        weapon.attack(x, y)
    }
}

private fun main() {
    val roadPetrolArmy = CustomArmy(Batten(), Jeep())
    roadPetrolArmy.move(10, 20)
    roadPetrolArmy.attackEnemy(10, 20)

    val indianArmy = CustomArmy(Gun(), Truck())
    indianArmy.move(100, 200)
    indianArmy.attackEnemy(100, 200)

    val indianAirForce = CustomArmy(Missile(), FighterJet())
    indianAirForce.move(1000, 2000)
    indianAirForce.attackEnemy(1000, 2000)
}

