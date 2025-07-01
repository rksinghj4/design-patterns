package com.example.solidprinciple

//Open close principle: Open for extension and closed for modification
//You should be able to extend a class behaviour, without modifying it.
class Operations {
    fun calculate(a: Double, b: Double, operationType: OperationType) = when (operationType) {
        OperationType.ADD -> a + b
        OperationType.SUB -> a - b
    }
}

enum class OperationType {
    ADD,
    SUB,
}

//Problem with above approach:
// If We want to add MUL/DIV operation then we have to modify the Operations Class
//Benefits: Testing efforts will be less.


/**
 * Solution:Tomorrow if we want to add new feature then we don't need to modify the existing ones.
 * Rather we can extend and add n number of new features.
 */

interface Operation {
    //Now it is closed for modification and open for extension
    fun calculate(a: Double, b: Double): Double
}

class AddOperation : Operation {
    //Now it has single responsibility i.e add
    override fun calculate(a: Double, b: Double) = a + b
}

class SubOperation : Operation {
    //Now it has single responsibility i.e. sub
    override fun calculate(a: Double, b: Double) = a - b
}

class Calculator(private var operation: Operation) {//Composition

    fun setOperation(operation: Operation) {
        this.operation = operation
    }

    inline fun <reified T> getType(): Class<T> {
        return T::class.java
    }

    fun calculate(a: Double, b: Double) = operation.calculate(a, b)
}

private fun main() {
    val calculator = Calculator(AddOperation())
    val result1 = calculator.calculate(4.0, 2.0)
    println("${calculator.getType<AddOperation>()} Result = $result1")
    calculator.setOperation(SubOperation())
    val result2  = calculator.calculate(4.0, 2.0)
    println("${calculator.getType<SubOperation>()} Result = $result2")

}

