package ex15

// 중위 함수(Infix function)

// fun <K, V> pair(first: K, second: V) = Pair(first, second)

// Java / Kotlin
//   = Generic Argument - Any 취급됩니다.

infix fun <K, V> K.pair(second: V) = Pair(this, second)
// => 인자가 한개인 '메소드' 는 중위 함수가 될 수 있습니다.

class User {
    infix fun move(x: Int) {

    }
}

// DSL(Domain Specific Language)

fun main() {
    val user = User()
    user.move(10)

    // 42.pair("xxx")

    // val pair1: Pair<String, Any> = "name".pair("Tom") // pair("name", "Tom")
    // val pair2: Pair<String, Any> = "age".pair(42)     // pair("age", 42)
    val pair1: Pair<String, Any> = "name" to "Tom"
    val pair2: Pair<String, Any> = "age" pair 42

    val json: Map<String, Any> = mapOf(
        pair1,
        pair2,
    )

    println(json)
}

// iOS
//  UI: Storyboard(xml)
//  BL: Swift

// => iOS 13
//   SwiftUI

// SWT
//   UI: Java
//   BL: Java

// Android
//  - UI: xml
//    B.L: Java + xml
//  UI: Kotlin(DSL) => Jetpack Compose
//  BL: Kotlin

// Flutter








