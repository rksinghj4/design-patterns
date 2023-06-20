package com.example.solidprinciple

//Single responsibility principle: A class should have only one reason to change.
// It means it should have one and only one responsibility.

class Book {
    val bookName: String = ""
    val authorName: String = ""
    lateinit var text: String

    fun findByName(name: String) = bookName.contains(name)
    fun findByAuthor(author: String) = authorName.contains(author)
    //Against Single Responsibility Principle.
    fun printTextToConsole(text: String) {
        //If we use printer or console dependency here the it is introducing coupling, Which is wrong.
    }
}

//We should separate the printing responsibility
//BookPrinter's responsibility is to convert book object to string and print it to console or email it.

class BookPrinter() {

    fun printTextToConsole(text: String) {

    }
    //Share text to other medium like Logger or Email
    fun shareTextToOtherMedium(text: String) {

    }
}


