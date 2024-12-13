package com.example.designpatternsdemo.behavioraldp.statepattern

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

data object Stop : Mode {
    override fun actAsPerTheMode() {
        println("Stop the running file")
    }
}

interface WhatCanHappenedInMediaPlay {
    fun play()
    fun stop()
    fun pause()
}

class MediaPlayer : WhatCanHappenedInMediaPlay {
    private var mode: Mode = Stop
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