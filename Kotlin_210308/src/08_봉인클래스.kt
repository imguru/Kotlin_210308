// 08_봉인클래스.kt
package ex8

import java.lang.Exception

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

// 3. Java:   switch-case
//    Kotlin: switch-case 문이 존재하지 않습니다.
//            => when expression 을 사용해야 합니다.

// Statement(문): 결과값이 존재하지 않습니다.
// Expression(식): 결과값이 존재합니다.
fun getName(color: Color): String {
    // when
    /*
    when (color) {
        Color.RED -> return "Red"
        Color.GREEN -> return "Green"
        Color.ORANGE -> return "Orange"
        Color.YELLOW -> return "Yellow"
    }
    */
    return when (color) {
        Color.RED -> "Red"
        Color.GREEN -> "Green"
        Color.ORANGE -> "Orange"
        Color.YELLOW -> "Yellow"
    }
}

// fallthrough - switch-case문에서 의도적으로 break 사용하지 않음.
fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.ORANGE -> "warm"
        Color.YELLOW -> "neutral"
        Color.GREEN -> "cold"
    }
}

// Set<Color>
// 임의 객체를 비교할 수 있다.
fun mix(c1: Color, c2: Color): Color {
    return when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        else -> throw Exception("Dirty color")
    }
}


fun main() {
    val color = Color.RED
    println(color.r)
    println(color.rgb())

    println(getName(color))
}
