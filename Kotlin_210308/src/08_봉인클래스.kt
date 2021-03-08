// 08_봉인클래스.kt
package ex8

// enum

// 1. enum class
// enum class Color {
//     RED, ORANGE, YELLOW, GREEN
// }

// 2. enum 은 Java와 동일하게 프로퍼티와 메소드를 가질 수 있습니다.
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0);
    // ; - 메소드를 정의할 경우 반드시 분리하는 목적으로 사용해야 합니다.

    fun rgb(): Int {
        return (r * 255 + g) * 255 + b
    }
}

fun main() {
    val color = Color.RED
    println(color.r)
    println(color.rgb())
}
