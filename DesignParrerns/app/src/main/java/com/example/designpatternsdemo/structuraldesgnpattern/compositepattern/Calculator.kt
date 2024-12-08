package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

interface ArithmeticExpression {
    fun evaluate(): Int
}

//Leaf object
class Number(private val number: Int) : ArithmeticExpression {
    override fun evaluate(): Int {
        println("Number = $number")
        return number
    }
}

//Composite object
class Expression(
    private val leftExp: ArithmeticExpression,
    private val rightExp: ArithmeticExpression,
    private val operation: Operation
) : ArithmeticExpression {
    override fun evaluate(): Int {
        val result = when (operation) {
            Operation.ADD -> leftExp.evaluate() + rightExp.evaluate()
            Operation.SUB -> leftExp.evaluate() - rightExp.evaluate()
            Operation.MUL -> leftExp.evaluate() * rightExp.evaluate()
            Operation.DIV -> leftExp.evaluate() / rightExp.evaluate()
        }
        println("Result = $result")
        return result
    }
}

enum class Operation {
    ADD, SUB, MUL, DIV
}

/**
 * Evaluate:
 *              *
 *            /  \
 *          4      +
 *               /  \
 *              5    8
 *
 */

private fun main() {
    val four = Number(number = 4)//Leaf
    val five = Number(number = 5)//Leaf
    val eight = Number(number = 8)//Leaf

    val addExp = Expression(leftExp = five, rightExp = eight, operation = Operation.ADD)
    val mulExp = Expression(leftExp = four, rightExp = addExp, operation = Operation.MUL)
    //Same operation can be executed on Leaf as well as on composite object
    mulExp.evaluate()
}