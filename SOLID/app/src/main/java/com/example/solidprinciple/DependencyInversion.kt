package com.example.solidprinciple

/**
 * Dependency inversion: Used for decouple the modules
 * High level modules should not depend on low level modules - both should depend on abstraction.
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

interface Mouse {
    fun move() {

    }
}

class WiredKeyBoard1 : KeyBoard {
    override fun type() {
        println("Wired KeyBoard: Typing")
    }
}

class WiredMouse1 : Mouse {
    override fun move() {
        println("Wired Mouse: Moving")
    }
}

class BlueToothKeyBoard : KeyBoard {
    override fun type() {
        println("BlueTooth KeyBoard: Typing")
    }
}

class BlueToothMouse : Mouse {
    override fun move() {
        println("BlueTooth Mouse: Moving")
    }
}

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
