package com.example.designpatternsdemo.behavioraldp.iteratorpattern

/**
 * The Iterator pattern is a behavioral design pattern that provides a way to access the elements
 * of a collection sequentially without exposing its underlying representation.
 * By extracting the traversal logic into a separate iterator object, the pattern decouples the client code
 * from the specific implementation of the collection, promoting greater flexibility and code reusability.
 */

/**
 * The Iterator pattern consists of four main parts:
 * Iterator (Interface): Declares the interface for accessing and traversing the elements.
 * It typically includes methods like hasNext() to check if there are more elements and next()
 * to retrieve the next one.
 *
 * ConcreteIterator: Implements the Iterator interface for a specific type of collection. It keeps track of the current position in the traversal.
 *
 * Aggregate (Interface): Declares a method for creating an Iterator object.
 * This is often named createIterator() or getIterator().
 *
 * ConcreteAggregate: Implements the Aggregate interface and returns a new instance
 * of the appropriate ConcreteIterator for its internal structure.
 *
 */
data class Book(
    val name: String, val price: Float
)

interface Iterator {
    fun hasNext(): Boolean
    fun next(): Any?
}

//Concrete Iterator: It has to maintain the reference of collection.
class BookIterator(private val books: List<Book>) : Iterator {
    private var index: Int = 0
    override fun hasNext(): Boolean = index < books.size
    override fun next(): Book? {
        if (hasNext()) {
            return books[index++]
        }
        return null
    }
}

interface Aggregate {
    fun createIterator(): Iterator
}

//Concrete Aggregator: It has to maintain the reference of collection.
class Library(private val books: List<Book>) : Aggregate {
    override fun createIterator(): Iterator = BookIterator(books)
}

private fun main() {
    val books = mutableListOf<Book>(
        Book(name = "DS", price = 100F),
        Book(name = "DAA", price = 110F),
        Book(name = "OS", price = 120F),
    )

    val lib = Library(books = books)
    val iterator = lib.createIterator()
    //For client iterator logic will be same for any Concrete Aggregator
    while (iterator.hasNext()) {
        val book = iterator.next()
        println("${(book as? Book)?.name}")
    }

}