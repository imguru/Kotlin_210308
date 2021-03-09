// 13_확장함수.kt
package ex13

class User {
    fun move(x: Int, y: Int) {
        println("User move")
    }
}

fun main() {
    val fn = User::move
}