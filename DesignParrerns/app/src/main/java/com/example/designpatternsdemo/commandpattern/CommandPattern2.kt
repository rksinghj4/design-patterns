package com.example.designpatternsdemo.commandpattern

//Command interface
interface CalCommand {
    fun execute(value: Int = 0): Int
    fun redo(value: Int = 0): Int
    fun undo(value: Int = 0): Int
}

//Concrete Commands
class AddCommand(private val calculator: CalculatorSoftware) : CalCommand {
    override fun execute(value: Int): Int = calculator.add(value)
    override fun undo(value: Int): Int = calculator.sub(value)
    override fun redo(value: Int): Int = calculator.add(value)
}

class ResultCommand(private val calculator: CalculatorSoftware) : CalCommand {
    override fun execute(value: Int): Int = calculator.result()
    override fun undo(value: Int): Int = calculator.result()
    override fun redo(value: Int): Int = calculator.result()
}


//Software as Receiver
class CalculatorSoftware(private var currentValue: Int = 0) {
    fun add(valueToAdd: Int): Int {
        currentValue += valueToAdd
        return currentValue
    }

    fun sub(valueToSubtract: Int): Int {
        currentValue -= valueToSubtract
        return currentValue
    }

    fun result() = currentValue
}
//New functionality can be added to the receiver without altering the existing functionalities.
//For new functionality we need to add new command.

//Hardware as Controller
class CalculatorKeyBoard(private var calCommand: CalCommand) {
    private val undoStack = ArrayDeque<Pair<CalCommand, Int>>()

    private val redoStack = ArrayDeque<Pair<CalCommand, Int>>()

    fun setCommand(command: CalCommand) {
        calCommand = command
    }

    fun pressButton(value: Int) {
        calCommand.execute(value)
        undoStack.addLast(Pair(calCommand, value))
    }

    fun undoButton() {
        val top = undoStack.removeLastOrNull()
        top?.first?.undo(top.second)
        top?.let {
            redoStack.addLast(top)
        }
    }

    fun redoButton() {
        val top = redoStack.removeLastOrNull()
        top?.first?.redo(top.second)
        top?.let {
            undoStack.addLast(top)
        }
    }

    fun result() = calCommand.execute()
}

private fun main() {
    //First create object of receiver
    val calculatorSoftware = CalculatorSoftware()
    //Command knows which receiver will execute the command.
    val addCommand = AddCommand(calculatorSoftware)

    //Controller knows about the command
    val calculatorKeyBoard = CalculatorKeyBoard(addCommand)
    calculatorKeyBoard.pressButton(1)

    val resultCommand = ResultCommand(calculatorSoftware)
    calculatorKeyBoard.setCommand(resultCommand)

    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.undoButton()
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.setCommand(addCommand)
    calculatorKeyBoard.pressButton(2)
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.undoButton()
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.redoButton()
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.redoButton()
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.undoButton()
    println("Result = ${calculatorKeyBoard.result()}")

    calculatorKeyBoard.undoButton()
    println("Result = ${calculatorKeyBoard.result()}")
}


