// 17_함수합성.kt
package ex17

// f(x) -> y
// g(y) -> z

// h(x) -> z

// x -> f(x)  -> y ->  g(y)  ->  z

fun compose1(f: (String) -> Int, g: (Any) -> Int) : (String) -> Int = { x ->
    val y = f(x)
    val z = g(y)
    z
}

fun compose(f: (String) -> Int, g: (Any) -> Int) : (String) -> Int = { x ->
    g(f(x))
}


// String.length : (String) -> Int  : f(x)
// Any.hashCode  : (Any) -> Int     : g(y)

fun main() {
    val str = "hello"
    println(str.length)   // (String) -> Int

    println(str.hashCode())
}