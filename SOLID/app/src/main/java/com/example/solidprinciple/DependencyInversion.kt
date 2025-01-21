package com.example.solidprinciple

/**
 * Dependency inversion: Used for decouple the modules
 * High level modules/class should not depend on low level modules/class - both should depend on abstraction.
 *
 * Abstraction should not depend on details. Details should depend on abstraction.
 *
 * Class should depend upon interfaces rather than concrete classes.
 */

class WiredKeyBoard {
    fun type() {
        println("Wired KeyBoard: Typing")
    }
}

class WiredMouse {
    fun move() {
        println("WiredMouse: Moving")
    }
}

//High level class(i.e. Desktop) is directly dependent on low level classes(i.e. WiredMouse)

class Desktop {
    //Here using concrete class objects to initialize the dependencies.
    // Which is violation of Dependency inversion principle.
    var wiredKeyBoard: WiredKeyBoard = WiredKeyBoard()
    var wiredMouse: WiredMouse = WiredMouse()
}

/**
 * Solution
 */

interface KeyBoard {
    fun type() {
    }
}

//Abstraction Mouse is defined based on what is needed for high level class(i.e MacBook).
interface Mouse {
    fun move() {

    }
}

//Low level class (LLC)
class WiredKeyBoard1 : KeyBoard {
    override fun type() {
        println("Wired KeyBoard: Typing")
    }
}

//Low level class (LLC)
class WiredMouse1 : Mouse {
    override fun move() {
        println("Wired Mouse: Moving")
    }
}

//Low level class
class BlueToothKeyBoard : KeyBoard {
    override fun type() {
        println("BlueTooth KeyBoard: Typing")
    }
}

//Low level class
class BlueToothMouse : Mouse {
    override fun move() {
        println("BlueTooth Mouse: Moving")
    }
}

//MacBook is our high level class, It needs mouse's move method so we need to have
// interface Mouse with move method
class MacBook(//Here using the interface for dependencies.
    var keyBoard: KeyBoard,
    var mouse: Mouse
) {
    fun startMac() {
        println("Starting mac")
        keyBoard.type()
        mouse.move()
    }
}

fun main() {
    MacBook(WiredKeyBoard1(), BlueToothMouse()).startMac()
    MacBook(BlueToothKeyBoard(), BlueToothMouse()).startMac()
}
