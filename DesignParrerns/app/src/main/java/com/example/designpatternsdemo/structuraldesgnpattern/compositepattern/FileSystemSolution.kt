package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

//Composite design pattern: Object inside object
//Composite pattern: Treats individual objects and group/compositions of objects uniformly.
internal interface FileSystem {
    fun ls()
}

//Leaf node
class MyFile(private val fileName: String) : FileSystem {
    override fun ls() {
        println("File name: $fileName")
    }
}

//Composite object
class MyDirectory(private val dirName: String) : FileSystem {
    //Note: Any is replace with FileSystem interface
    private val directories: MutableList<FileSystem> = lazy { mutableListOf<FileSystem>() }.value

    internal fun add(obj: FileSystem) {
        directories.add(obj)
    }

    override fun ls() {
        println("Dir name: $dirName")
        directories.forEach {
            /*
            if (it is Directory) {
                it.ls()
            } else if (it is File) {
                it.ls()
            }
            */
            /**
             * Prob solved: Same operation can be executed on individual object and their collection
             */
            it.ls()
        }
    }
}

private fun main() {
    val moviesDir = MyDirectory(dirName = "Movies")
    val hollywoodDir = MyDirectory(dirName = "Hollywood")
    hollywoodDir.add(MyFile(fileName = "Troy"))
    hollywoodDir.add(MyFile(fileName = "300"))

    val bollywoodDir = MyDirectory(dirName = "Bollywood")
    bollywoodDir.add(MyFile(fileName = "Pushpa"))
    bollywoodDir.add(MyFile(fileName = "Vijaypath"))

    moviesDir.add(hollywoodDir)
    moviesDir.add(bollywoodDir)

    moviesDir.ls()
}