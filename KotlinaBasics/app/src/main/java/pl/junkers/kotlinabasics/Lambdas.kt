package pl.junkers.kotlinabasics

fun main() {

    val sum: (Int, Int) -> String = { a: Int, b: Int -> "${a + b}" }
    val add = { a: Int, b: Int -> a + b }
    println(add(10, 5))
    println(sum(10,5))
}