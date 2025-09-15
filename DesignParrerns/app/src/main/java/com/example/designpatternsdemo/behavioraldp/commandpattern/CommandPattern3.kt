package com.example.designpatternsdemo.behavioraldp.commandpattern

// 1. Command interface
//interface Command {
//    fun execute()
//}

// 2. Receiver
class Light {
    fun turnOn() = println("Light is ON")
    fun turnOff() = println("Light is OFF")
}

// 3. Concrete Commands
class TurnOnLightCommand(private val light: Light) : Command {
    override fun execute() = light.turnOn()
}

class TurnOffLightCommand(private val light: Light) : Command {
    override fun execute() = light.turnOff()
}

// 4. Invoker
class RemoteControl {
    private val commands = mutableListOf<Command>()

    fun submit(command: Command) {
        commands.add(command)
        command.execute()
    }
}

// 5. Client
fun main() {
    val light = Light()
    val remote = RemoteControl()

    val turnOn = TurnOnLightCommand(light)
    val turnOff = TurnOffLightCommand(light)

    remote.submit(turnOn)   // Light is ON
    remote.submit(turnOff)  // Light is OFF
}
