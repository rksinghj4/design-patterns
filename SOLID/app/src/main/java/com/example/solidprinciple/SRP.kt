package com.example.solidprinciple

/**
 * https://proandroiddev.com/solid-design-principles-in-kotlin-79100c670df1
 *
 */

//Single responsibility principle: A class should have only one reason to change.
// It means it should have one and only one responsibility.

class Book {
    val bookName: String = ""
    val authorName: String = ""
    lateinit var text: String

    fun findByName(name: String) = bookName.contains(name)
    fun findByAuthor(author: String) = authorName.contains(author)

    fun editBook() {
        text = "Here you can add/delete/replace"
    }
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

/**
 * Here, UserManager has 3 responsibilities:
 * Authentication
 * Database persistence
 * Email sending
 * If login logic, database, or email system changes → we need to modify this class → SRP is broken.
 */
class UserManager {

    fun login(username: String, password: String): Boolean {
        // Business logic
        println("Checking user credentials...")
        return username == "admin" && password == "1234"
    }

    fun saveUserToDb(user: String) {
        // Database logic
        println("Saving $user to database")
    }

    fun sendEmail(user: String) {
        // Email logic
        println("Sending welcome email to $user")
    }
}
/**
 * Solution: We can separate these responsibilities into different classes.
 * UserManager2 now has only one responsibility: user registration.
 * If any of the other systems change, we only need to modify their respective classes.
 */

class UserManager2(
    private val authService: AuthService,
    private val userRepository: UserRepository,
    private val emailService: EmailService
) {
    fun registerUser(username: String, password: String) {
        if (authService.login(username, password)) {
            userRepository.saveUser(username)
            emailService.sendWelcomeEmail(username)
        }
    }
}

/**
 * Now:
 * AuthService → only authenticates.
 * UserRepository → only handles persistence.
 * EmailService → only sends emails.
 * UserManager → coordinates them.
 * Each class has one reason to change → this is SRP in action.
 */

class AuthService {
    fun login(username: String, password: String): Boolean {
        println("Checking user credentials...")
        return username == "admin" && password == "1234"
    }
}

class UserRepository {
    fun saveUser(user: String) {
        println("Saving $user to database")
    }
}

class EmailService {
    fun sendWelcomeEmail(user: String) {
        println("Sending welcome email to $user")
    }
}




