package com.example.designpatternsdemo.behavioraldp.commandpattern

/**
 * The Command Pattern encapsulates an action/request as an object, decoupling the sender (Invoker)
 * from the receiver, making it easy to parameterize actions, queue them,
 * or implement undo/redo functionality.
 *
 * Encapsulate a request as an object by binding together a set of actions on a specific receiver.
 * It decouples the sender of a request (Invoker) from the receiver of the request (Receiver),
 * which is the object that performs the actual operation.
 *
 * This pattern is useful in scenarios where you want to parameterize objects with operations,
 * queue operations, log operations, or support undoable operations.
 * Perform the command in order and undo in the reverse order.
 */

interface Command {
    fun execute()
}

/**
 * Concrete Command knows about the device and override the execute method according to
 */

//Concrete Commands: Each command is responsible for a specific action of the device.
class TurnOnCommand(
    private val fan: Fan//Receiver/device
) : Command {
    override fun execute() {
        fan.switchOn()
    }
}

class TurnOffCommand(
    private val fan: Fan//Device/receiver
) : Command {
    override fun execute() {
        fan.switchOff()
    }
}

//To support new functionality in the current device we can implement
// a new command without affecting the existing ones.

/**
 * Device knows about all its operations/actions and how to perform each action.
 *
 * To support each functionality of device we need a command to operate.
 */
//Receiver as hardware device
class Fan {
    fun switchOn() {
        println("Switching on fan")
    }

    fun switchOff() {
        println("Switching off fan")
    }

    //A new functionality can be added in future without affecting the existing ones.
}

//A new Device can be added in future without affecting the existing devices and their commands.
//For new device we can add new commands without affecting the existing code.

/**
 * Controller knows about the command
 */

//Controller/Invoker
class SwitchBoard(private var command: Command) {
    fun setCommand(command: Command) {
        this.command = command
    }

    fun pressButton() {
        command.execute() //Controller is the command invoker
    }
}

//main function will act as client for controller
private fun main() {
    //First create object of receiver
    val fan = Fan()
    //Command know which device/receiver to operate.
    val turnOnCommand = TurnOnCommand(fan)

    //Controller knows about the passed command and it will operate that.
    val switchBoard1 = SwitchBoard(turnOnCommand)

    //Controller doesn't know what to do when button is pressed, but passed command knows.
    switchBoard1.pressButton()///button is pressed when controller has TurnOnCommand

    val turnOffCommand = TurnOffCommand(fan)//Command know which device to operate.

    switchBoard1.setCommand(turnOffCommand)
    switchBoard1.pressButton()//Same button is pressed with TurnOffCommand
}

