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
     BLUE(0, 0, 255),
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
        Color.BLUE -> "Blue"
    }
}

// fallthrough - switch-case문에서 의도적으로 break 사용하지 않음.
fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.ORANGE -> "warm"
        Color.YELLOW -> "neutral"
        Color.GREEN, Color.BLUE -> "cold"
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

/*
fun main() {
    val color = Color.RED
    println(color.r)
    println(color.rgb())

    println(getName(color))
}
*/

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Num, val right: Num) : Expr
// class Diff(val left: Num, val right: Num) : Expr

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else ->
            throw IllegalArgumentException("Unknown expr")
    }
}

fun main() {
    val left = Num(10)
    val right = Num(32)
    val sum = Sum(left, right)

    val result = eval(sum)
    println(result)
}
*/
/*
sealed class Expr {
    // 봉인된 클래스: Num, Sum 이외의 자식 클래스는 존재하지 않는다.
    class Num(val value: Int) : Expr()
    class Sum(val left: Num, val right: Num): Expr()
    // class Diff(val left: Num, val right: Num) : Expr()
}
*/

// 같은 파일 안에서 봉인된 클래스에 대한 자식 클래스를 허용한다.
sealed class Expr {
    open var name: String = "Expr"
}


class Num(val value: Int) : Expr() {
    override var name = "Num"
}

class Sum(val left: Num, val right: Num): Expr()

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
}

fun main() {
    val left = Num(10)
    val right = Num(32)
    val sum = Sum(left, right)

    val result = eval(sum)
    println(result)

    val n = 100
    val result2 = when (n) {
        10 -> "xxx"
        20 -> "yyy"
        else -> "error"
    }

}











