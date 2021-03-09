// 14_확장프로퍼티.kt
//  1. Backing Field가 있는 프로퍼티
//  2. Backing Field가 없는 프로퍼티 - 확장 프로퍼티

package ex14

// fun String.lastChar() = this[length - 1]

// 확장 프로퍼티
val String.lastChar: Char
    get() = this[length - 1]

fun main() {
    val result = "hello".lastChar

    // val result = "hello".lastChar()
    println(result)
}