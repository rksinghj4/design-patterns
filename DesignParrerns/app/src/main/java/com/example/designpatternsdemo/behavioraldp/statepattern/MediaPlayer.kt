package com.example.designpatternsdemo.behavioraldp.statepattern

/**
 * State DP: It is behavioural dp that allows an object to change its behavior
 * when its internal state changes. This pattern is particularly useful when an object’s behavior
 * depends on its state, and the state can change during the object’s lifecycle.
 *
 * This pattern focuses on managing state transitions and coordinating state-specific behaviors.
 *
 */

sealed interface Mode {
    fun actAsPerTheMode()
}

data object Play : Mode {
    override fun actAsPerTheMode() {
        println("Play selected file")
    }
}

data object Pause : Mode {
    override fun actAsPerTheMode() {
        println("Pause the running file")
    }
}

data object Stop : Mode {//We make data object when we have no state to preserve.
    override fun actAsPerTheMode() {
        println("Stop the running file")
    }
}

interface WhatCanHappenedInMediaPlayer {
    fun play()
    fun stop()
    fun pause()
}

class MediaPlayer(var mode: Mode = Stop) : WhatCanHappenedInMediaPlayer {
    override fun play() {
        mode = Play
    }

    override fun stop() {
        mode = Stop
    }

    override fun pause() {
        mode = Pause
    }

    fun action() {
        mode.actAsPerTheMode()
    }
}

private fun main() {
    val mediaPlayer = MediaPlayer()
    mediaPlayer.play()
    mediaPlayer.action()

    mediaPlayer.pause()
    mediaPlayer.action()

    mediaPlayer.stop()
    mediaPlayer.action()
}