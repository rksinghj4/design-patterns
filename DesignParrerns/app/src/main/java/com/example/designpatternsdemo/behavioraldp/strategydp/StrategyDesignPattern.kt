package com.example.designpatternsdemo.behavioraldp.strategydp

/**
 * https://www.geeksforgeeks.org/strategy-pattern-set-1/
 *
 * To sort input data, we can have different strategy/algo
 * like Quick sort, merge sort, selection sort, bubble sort etc.
 *
 * Based on the need we can choose and set required strategy to apply.
 */
interface DiscountStrategy {
    //ISP followed
    fun giveDiscount()
}

class FlatDiscount : DiscountStrategy {
    //SRP followed
    override fun giveDiscount() {
        println("Flat discount")
    }
}

class CouponDiscount : DiscountStrategy {
    override fun giveDiscount() {
        println("Coupon discount")
    }
}

/**
 * If tomorrow new discount strategy come up we will override giveDiscount method with
 * required implementation.
 */

class Cashback : DiscountStrategy {
    override fun giveDiscount() {
        println("Cashback discount")
    }
}

/**
 * 1. Context
 * The Context is a class or object that holds a reference to a strategy object and delegates the task to it.
 *
 * It acts as the interface between the client and the strategy,
 * providing a unified way to execute the task without knowing the details of how it’s done.
 * The Context maintains a reference to a strategy object and calls its methods to perform the task,
 * allowing for interchangeable strategies to be used.
 */
class ApplyDiscount(private var discountStrategy: DiscountStrategy) {
    //Composition of changing behaviour
    //DIP followed
    fun setStrategy(discountStrategy: DiscountStrategy) {
        this.discountStrategy = discountStrategy
    }

    fun giveDiscount() {
        //Delegates the task to respective strategy.
        //Context class is unaware of how the task has been implemented inside concrete strategy
        discountStrategy.giveDiscount()
    }
}

class ApplyDiscountUsingDeligation(var discountStrategy: DiscountStrategy) :
    DiscountStrategy by discountStrategy {
    //Composition of changing behaviour
      /*  // DIP followed
    fun setStrategy(discountStrategy: DiscountStrategy) {
        this.discountStrategy = discountStrategy
    }

    fun giveDiscount() {
        //Delegates the task to respective strategy.
        //Context class is unaware of how the task has been implemented inside concrete strategy
        discountStrategy.giveDiscount()
    }*/
}

private fun main() {
    val applyDiscount = ApplyDiscount(FlatDiscount())
    applyDiscount.giveDiscount()

    applyDiscount.setStrategy(CouponDiscount())
    applyDiscount.giveDiscount()

    /**
     * At run time we can set another strategy
     *
     * Behavior of an object to be selected at runtime.
     */
    applyDiscount.setStrategy(Cashback())
    applyDiscount.giveDiscount()
    println("_________________________________________")
    val applyDiscountByDeligation = ApplyDiscountUsingDeligation(FlatDiscount())
    applyDiscountByDeligation.giveDiscount()
    applyDiscountByDeligation.discountStrategy = CouponDiscount() //doesn't change the strategy
    applyDiscountByDeligation.giveDiscount()
}