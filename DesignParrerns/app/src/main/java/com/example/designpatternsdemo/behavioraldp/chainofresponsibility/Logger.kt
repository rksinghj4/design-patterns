package com.example.designpatternsdemo.behavioraldp.chainofresponsibility

//LogProcessor / LogHandler
interface LogHandler {
    fun handleLog(logLevel: Int, infoToLog: String)

    companion object {
        const val INFO = 1
        const val DEBUG = 2
        const val ERROR = 3
    }
}

class InfoLogProcessor(private val next: LogHandler?) : LogHandler {
    override fun handleLog(logLevel: Int, infoToLog: String) {
        if (logLevel == LogHandler.INFO) {
            println("INFO: $infoToLog")
        } else {
            next?.handleLog(logLevel, infoToLog)
        }
    }
}

class DebugLogProcessor(private val next: LogHandler?) : LogHandler {
    override fun handleLog(logLevel: Int, infoToLog: String) {
        if (logLevel == LogHandler.DEBUG) {
            println("DEBUG: $infoToLog")
        } else {
            next?.handleLog(logLevel, infoToLog)
        }
    }
}

class ErrorLogProcessor(private val next: LogHandler?) : LogHandler {
    override fun handleLog(logLevel: Int, infoToLog: String) {
        if (logLevel == LogHandler.ERROR) {
            println("ERROR: $infoToLog")
        } else {
            next?.handleLog(logLevel, infoToLog)
        }
    }
}

private fun main() {
    val logger = InfoLogProcessor(DebugLogProcessor(ErrorLogProcessor(null)))
    //Keep delegating to next handler until handled:
    // InfoLogProcessor -> DebugLogProcessor -> ErrorLogProcessor - finally handled
    logger.handleLog(LogHandler.ERROR, "Unexpected Error")
    //InfoLogProcessor -> DebugLogProcessor - finally handled
    logger.handleLog(LogHandler.DEBUG, "You need to debug your xyz code/function ")
    //Keep delegating to next handler until handled: InfoLogProcessor - finally handled
    logger.handleLog(LogHandler.INFO, "Things happened smoothly")
}