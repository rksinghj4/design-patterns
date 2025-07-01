package com.example.designpatternsdemo.structuraldesgnpattern.compositepattern

/**
 * The Composite Pattern lets you treat individual objects and groups of objects in the same way.
 *
 * It's commonly used when you have a tree structure — like a file system, UI layout, or organization hierarchy.
 *
 * Whenever we encountered a problem in tree like structure(Inheritance) mostly
 * it can be solved using composite pattern.
 *
 * Component (interface)
 *  ├── Leaf (single item)
 *  └── Composite (group of components)
 *
 */

interface UIComponent {
    fun draw()
}

class TextView(private val text: String) : UIComponent {
    override fun draw() {
        println("Textview: drawing with: $text")
    }
}

class Imageview(private val url: String) : UIComponent {
    override fun draw() {
        println("Imageview: drawing with $url")
    }
}

class ViewGroup(private val name: String) : UIComponent {
    private val viewElements: Lazy<MutableList<UIComponent>> = lazy { mutableListOf<UIComponent>() }

    fun add(uiComponent: UIComponent) {
        viewElements.value.add(uiComponent)
    }

    override fun draw() {
        println("ViewGroup: drawing: $name")
        viewElements.value.forEach {
            it.draw()//Same operation is allowed on each node and entire tree.
        }
    }
}

private fun main() {
    val viewGroup = ViewGroup("Constraint layout")
    viewGroup.add(TextView(text = "Composite pattern"))
    viewGroup.add((Imageview(url = "xpz")))
    viewGroup.draw()
}


