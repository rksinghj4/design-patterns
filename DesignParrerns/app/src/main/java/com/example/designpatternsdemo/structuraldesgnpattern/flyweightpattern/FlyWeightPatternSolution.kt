package com.example.designpatternsdemo.structuraldesgnpattern.flyweightpattern

/**
 * It is Structural DP, It drastically reduce the memory uses by sharing data among multiple objects.
 *
 * When to use:
 *  when we have limited memory and objects share the data among themself
 *  then separate out
 *  Intrinsic data: Remains same once defined one value of that kind(e.g Hulk image) and
 *  share among multiple objects.
 *  Extrinsic data: Changes based on client input and differs from one objects to another.
 */

/**
 * How to solve the issue:
 * Form object - remove all extrinsic data just keep intrinsic data(It is called Flyweight/lightweight object)
 * Make the Flyweight class immutable.
 * Extrinsic data can be passed to flyweight class in method parameter.
 *
 * Once flyweight object is created, it is cached and reused whenever needed.
 * Hint: In kotlin we can make singleton object for data sharing.
 */


data class Sprites1(
    val images: String = "Hulk.jpg"
)


interface RobotI {
    fun display(x: Int, y: Int)
}

class BatManRobot(
    val type: String, //Intrinsic
    val sprites: Sprites, //Intrinsic
    //x: Int, //removed Extrinsic
    //y: Int, //removed Extrinsic
) : RobotI {
    override fun display(x: Int, y: Int) {
        println("Display $this at x = $x, y = $y")
    }
}

class HulkRobot(
    val type: String, //Intrinsic
    val sprites: Sprites, //Intrinsic
    //x: Int, //removed Extrinsic
    //y: Int, //removed Extrinsic
) : RobotI {
    override fun display(x: Int, y: Int) {
        println("Display $this at x = $x, y = $y")
    }
}

class SuperManRobot(
    val type: String, //Intrinsic
    val sprites: Sprites, //Intrinsic - Remains same once defined one value of that kind
    //x: Int, //removed Extrinsic
    //y: Int, //removed Extrinsic
) : RobotI {
    override fun display(x: Int, y: Int) {
        println("Display $this at x = $x, y = $y")
    }
}

object SpritesObj {
    val batman = Sprites("batman.jpg")
    val hulk = Sprites("hulk.jpg")
    val superman = Sprites("superman.jpg")
}

object RobotObj {
    val batman = lazy { BatManRobot(RobotType.BATMAN.name, SpritesObj.batman) }.value
    val hulk = lazy { HulkRobot(RobotType.HULK.name, SpritesObj.hulk) }.value
    val superman = lazy { SuperManRobot(RobotType.SUPERMAN.name, SpritesObj.superman) }.value
}

class RobotFactory1 {
    //Note: By using singleton object for caching, we can avoid check if hashmap contains then get,
    // if not then put
    companion object {
        fun create(robotType: RobotType) = when (robotType) {
            RobotType.BATMAN -> RobotObj.batman
            RobotType.HULK -> RobotObj.hulk
            RobotType.SUPERMAN -> RobotObj.superman
        }
    }
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
    //Here we saved lot of memory by using the Flyweight class/object.
    (1..100).forEach {
        val robot =
            RobotFactory1.create(RobotType.HULK)//Same Hulk Robot object will be used 100 times
        robot.display(x + it, y + it)
    }

    (1..100).forEach {
        val robot =
            RobotFactory1.create(RobotType.BATMAN)//Same BATMAN Robot object will be used 100 times
        robot.display(x + it, y + it)
    }
}