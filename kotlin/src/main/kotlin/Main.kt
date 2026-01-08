package ly.algobase

fun main() {
    val name = "Kotlin"
    println("Hello, " + name + "!")

    for (i in 1..5) {
        println("i = $i")
    }
}

fun add(a: Int, b: Int): Int = a + b

fun greet(name: String): String = "Hello, $name"