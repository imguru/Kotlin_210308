// 17_함수합성.kt
package ex17

// f(x) -> y
// g(y) -> z
//--------------
// h(x) -> z

// x -> f(x)  -> y ->  g(y)  ->  z

fun add(x: Int, y: Int): Int {
    return x + y
}

fun add2(x: Int, y: Int): Int = x + y

fun compose0(f: (String) -> Int, g: (Any) -> Int): (String) -> Int {
    return { x ->
        val y = f(x)
        val z = g(y)
        z
    }
}

fun compose1(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    val y = f(x)
    val z = g(y)
    z
}

fun compose2(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    g(f(x))
}

// f(A) -> B
// g(B) -> C
//-----------
// h(A) -> C

// A -> f(A) -> B - > g(B) -> C -> h(C) -> D
// Java 8 - Stream API

// 1. 일반화
fun <A, B, C> compose3(f: (A) -> B, g: (B) -> C): (A) -> C = { x ->
    g(f(x))
}

// 2. 확장 함수 / 중위 함수
infix fun <A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C = { x ->
    g(this(x))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // val h = f.compose(g)  // f.compose(g)
    val h = f compose g

    val result = h("hello")
    println(result)
}


/*
// String.length : (String) -> Int  : f(x)
// Any.hashCode  : (Any) -> Int     : g(y)
fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    val h = compose(f, g)
    val result = h("hello")
    println(result)

    // val str = "hello"
    // println(str.length)   // (String) -> Int
    // println(str.hashCode())
}
*/