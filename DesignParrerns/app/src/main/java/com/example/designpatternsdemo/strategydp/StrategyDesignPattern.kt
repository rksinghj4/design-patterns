package com.example.designpatternsdemo.strategydp

/**
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
 *
 */

class Cashback : DiscountStrategy {
    override fun giveDiscount() {
        println("Cashback discount")
    }
}

class ApplyDiscount(private var discountStrategy: DiscountStrategy) {
    //DIP followed
    fun setStrategy(discountStrategy: DiscountStrategy) {
        this.discountStrategy = discountStrategy
    }

    fun giveDiscount() {
        discountStrategy.giveDiscount()
    }
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
}