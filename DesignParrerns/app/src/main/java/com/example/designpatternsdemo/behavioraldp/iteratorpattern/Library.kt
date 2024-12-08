package com.example.designpatternsdemo.behavioraldp.iteratorpattern

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