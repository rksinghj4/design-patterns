package com.example.designpatternsdemo.behavioraldp.chainofresponsibility

data class Request(val email: String, val question: String, val expertise: Expertise)

enum class Expertise {
    FRESHER, TRAINEE, JUNIOR, SENIOR, EXPERT
}

data class Response(val answer: String)

interface Handler {
    fun handle(request: Request): Response?
}

class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response? {
        require(request.email.isNotBlank()) {
            println("Email must not be empty.")
        }
        require(request.question.isNotBlank()) {
            println("Question must not be empty.")
        }
        return next.handle(request)
    }
}

class KnownEmailHandler(private val next: Handler) : Handler {
    private val emails = mutableSetOf("abc@gmail.com", "xyz@gmail.com")
    override fun handle(request: Request): Response? {
        require(emails.contains(request.email)) {
            println("Your email is unknown.")
        }
        return next.handle(request)
    }
}

class TraineeDeveloperFilterHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response? {
        require(request.expertise.ordinal >= Expertise.JUNIOR.ordinal) {
            println("Expertise must be at least junior.")
        }
        return next.handle(request)
    }
}

class AutoAnswer : Handler {
    private val answers = hashMapOf<String, Response>()

    init {
        answers["android"] =
            Response(answer = "Android is an open-source operating system (OS) for mobile devices, such as smartphones and tablets")
        answers["kotlin"] =
            Response(answer = "Kotlin is a modern, open-source programming language that's used for writing clean, reliable, and maintainable code")
        answers["unknown"] = Response(answer = "We will get back to you soon")
    }

    override fun handle(request: Request): Response? {
        return answers.getOrDefault(request.question, answers["unknown"])
    }
}

private fun main() {
    val request =
        Request(email = "abc@gmail.com", question = "android", expertise = Expertise.SENIOR)

    val handler =
        BasicValidationHandler(KnownEmailHandler(TraineeDeveloperFilterHandler(AutoAnswer())))
    println("${handler.handle(request)?.answer}")

    println(
        "${
            handler.handle(
                request.copy(
                    email = "rst@gmail.com",
                    question = "kotlin"
                )
            )?.answer
        }"
    )
}

