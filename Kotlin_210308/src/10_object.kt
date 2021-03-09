// 10_object.kt
package ex10


//  1. object declaration
//   => Singleton을 생성할 때 사용하는 문법

object Cursor {
    fun move(x: Int, y: Int) {

    }
}

fun main() {
    Cursor.move(10, 20)
}