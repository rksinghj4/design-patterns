package com.example.designpatternsdemo.structuraldesgnpattern.flyweightpattern

/**
 * It is Structural DP, It drastically reduce the memory uses by sharing data among multiple objects.
 */

//Sprite - प्रेत
//Sprite synonyms -  phantom, sprite, apparition, ghost, demon, fiend

data class RobotImage(
    val images: String = "Hulk.jpg"
)

class Robot(
    val type: String, //Intrinsic
    val robotImage: RobotImage, //Intrinsic
    x: Int, //Extrinsic
    y: Int, //Extrinsic
)

class RobotImageFactory {
    companion object {
        fun create(robotType: RobotType) = when (robotType) {
            RobotType.BATMAN -> RobotImage("batman.jpg")
            RobotType.HULK -> RobotImage("hulk.jpg")
            RobotType.SUPERMAN -> RobotImage("superman.jpg")
        }
    }
}

enum class RobotType {
    HULK, BATMAN, SUPERMAN
}

/**
 * Problem: Here creation of object is memory consuming, Lets say each Sprite take 4KB space for image
 * then 100 objects take 100*4KB memory.
 *
 * Solution: Rather than having separate sprite(e.g HULK image) for each object we can share the same sprite
 * for rest of the objects.
 */
private fun main() {
    val x = 0
    val y = 0
    (1..100).forEach {
        val robot = Robot("hulk", RobotImageFactory.create(RobotType.HULK), x + it, y + it)
    }

    (1..100).forEach {
        val robot = Robot("batman", RobotImageFactory.create(RobotType.BATMAN), x + it, y + it)
    }
}