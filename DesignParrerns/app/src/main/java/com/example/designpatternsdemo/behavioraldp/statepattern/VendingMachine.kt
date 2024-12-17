package com.example.designpatternsdemo.behavioraldp.statepattern

import java.lang.IllegalStateException
import kotlin.Exception

// There are 3 states
//NoCoinState -> CoinInsertedState -> DispenseSate -> NoCoinState
interface VMState {
    fun insertCoin(amount: Double)

    fun pressedButton(aisleNumber: Int)

    fun dispense(aisleNumber: Int)
}

class NoCoinInsertedState(private val vendingMachine: VendingMachine) : VMState {
    override fun insertCoin(amount: Double) {
        println("Coin inserted: $amount")
        vendingMachine.setNewAmount(amount)
        vendingMachine.setCurrentState(States.coinInsertedState)
    }

    override fun pressedButton(aisleNumber: Int) {
        throw IllegalStateException("No coin inserted state")
    }

    override fun dispense(aisleNumber: Int) {
        throw IllegalStateException("No coin inserted state")
    }
}

class CoinInsertedState(private val vendingMachine: VendingMachine) : VMState {
    override fun insertCoin(amount: Double) {
        println("Coin inserted: $amount")
        vendingMachine.setNewAmount(vendingMachine.amount + amount)
    }

    override fun pressedButton(aisleNumber: Int) {
        println("pressed Button: $aisleNumber")

        val inventory = vendingMachine.getVendingMachineInventory()
        val product = inventory.getProductAt(aisleNumber)

        if (!vendingMachine.hasEnoughAmount(product?.price ?: 0.0)) {
            throw IllegalStateException("Has no sufficient amount to buy this product")
        }

        if (!inventory.isProductAvailable(product?.id ?: 0)) {
            throw IllegalStateException("Product not available in inventory")
        }
        //State transition
        vendingMachine.setCurrentState(States.dispenseState)
    }

    override fun dispense(aisleNumber: Int) {
        throw IllegalStateException("No coin inserted state")
    }
}


class DispenseState(private val vendingMachine: VendingMachine) : VMState {
    override fun insertCoin(amount: Double) {
        throw IllegalStateException("No coin inserted state")
    }

    override fun pressedButton(aisleNumber: Int) {
        throw IllegalStateException("No coin inserted state")
    }

    override fun dispense(aisleNumber: Int) {
        val inventory = vendingMachine.getVendingMachineInventory()
        val product = inventory.getProductAt(aisleNumber)
        println("Collect your product from tray: $product")
        inventory.deductProductCount(aisleNumber)
        val change = vendingMachine.amount - (product?.price ?: 0.0)
        if (change > 0) {
            println("Collect your remaining money: $change")
        }
        vendingMachine.setNewAmount(0.0)
        //State transition
        vendingMachine.setCurrentState(States.noCoin)
        println("Product $product is dispensed")
    }
}

object States {
    const val AISLE_COUNT = 2//Total types of product
    internal val vendingMachine = lazy { VendingMachine() }.value
    val noCoin = lazy { NoCoinInsertedState(vendingMachine) }.value
    val coinInsertedState = lazy { CoinInsertedState(vendingMachine) }.value
    val dispenseState = lazy { DispenseState(vendingMachine) }.value
}

class VendingMachine(
    var amount: Double = 0.0,
    internal var inventory: Inventory = Inventory(States.AISLE_COUNT)
) {
    private var currentState: VMState = NoCoinInsertedState(this)

    fun setNewAmount(amount: Double) {
        this.amount = amount
    }

    fun setCurrentState(state: VMState) {
        currentState = state
    }

    fun getVendingMachineInventory() = inventory

    fun hasEnoughAmount(expectedPrice: Double): Boolean {
        return expectedPrice <= amount
    }

    fun insertCoin(amount: Double) {
        //Runtime polymorphism is happening
        currentState.insertCoin(amount)
    }

    fun pressedButton(aisleNumber: Int) {
        currentState.pressedButton(aisleNumber)
        currentState.dispense(aisleNumber)
    }

    fun addProduct(product: Product) {
        try {
            inventory.addProduct(product)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

data class Product(val id: Int, val name: String, val price: Double)

class Inventory(
    var aisleCount: Int,
    private val aisleToProductMap: MutableMap<Int, Product> = mutableMapOf(),
    val productIdToCountMap: MutableMap<Int, Int> = mutableMapOf(),
    val availableAisle: ArrayDeque<Int> = ArrayDeque()
) {
    init {
        (1..aisleCount).forEach {
            availableAisle.add(it)
        }
    }

    fun getProductAt(aisleNumber: Int): Product? = aisleToProductMap.get(aisleNumber)

    fun isProductAvailable(productId: Int): Boolean =
        productIdToCountMap.getOrDefault(productId, 0) > 0

    fun deductProductCount(aisleNumber: Int) {
        val product = aisleToProductMap[aisleNumber]
        val productCount = productIdToCountMap.getOrDefault(product?.id, 0)
        if (productCount == 1) {//If it was the last product
            productIdToCountMap.remove(product?.id)
            aisleToProductMap.remove(aisleNumber)
            availableAisle.add(aisleNumber)//Add the aisleNumber back to availableAisle

        } else {
            product?.let { productIdToCountMap[product.id] = productCount - 1 }
        }
    }

    fun addProduct(product: Product) {
        val productCount = productIdToCountMap.getOrDefault(product.id, 0)
        if (productCount == 0) {//When very first item is added to the available aisle
            if (availableAisle.isEmpty()) {
                throw Exception("Out of space, can't add product")
            }
            aisleToProductMap[availableAisle.removeFirst()] = product
        }
        productIdToCountMap[product.id] = productCount + 1
    }
}

private fun main() {
    val coldDrink = Product(1, "Pepsi", 20.0)
    val biscuits = Product(2, "Biscuit", 10.0)

    //Admin will fill the inventory
    (1..2).forEach { _ ->
        States.vendingMachine.addProduct(coldDrink)
        States.vendingMachine.addProduct(biscuits)
    }

    //Consumer will buy products
    States.vendingMachine.insertCoin(10.0)
    States.vendingMachine.insertCoin(10.0)
    States.vendingMachine.pressedButton(1)

    States.vendingMachine.insertCoin(10.0)
    States.vendingMachine.insertCoin(5.0)
    States.vendingMachine.pressedButton(2)

    States.vendingMachine.insertCoin(10.0)
    States.vendingMachine.pressedButton(2)
}

