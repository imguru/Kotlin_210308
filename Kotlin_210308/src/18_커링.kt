// 18_커링.kt
package ex18

import java.lang.Appendable
import java.time.LocalDateTime

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업

// sum(10, 20, 30, 40)
//  커링 => sum(10)(20)(30)(40) => 110 결과 발생

// => 함수 실행을 지연할 수 있다.
// => 함수의 인자에 대해서 '부분 적용' 을 사용할 때

// Scala
//  fun sum(x: Int)(y: Int)(z: Int) -> Int

// sum(10, 20)  => 30
fun sum2(x: Int, y: Int): Int = x + y

// sum(x)(y)  => 30
/*
fun sum2(x: Int) : (Int) -> Int = { y: Int ->
    x + y
}
*/

fun xsum2(x: Int): (Int) -> Int = { y: Int ->
    x + y
}

// sum(10, 20, 30)
fun sum3(x: Int, y: Int, z: Int) = x + y + z

// sum(10)(20)(30) => 60
/*
fun sum3(x: Int) : (Int) -> (Int) -> Int = { y ->
    { z ->
        x + y + z
    }
}
*/


/*
fun main() {
    var result = sum2(10, 20)
    println(result)

    result = sum2(10)(20)
    println(result)

    result = sum3(10, 20, 30)
    println(result)

    result = sum3(10)(20)(30)
    println(result)
}
*/

//-------------------------
// '인자 2개인 함수'에 대해 커링된 버전의 함수를 생성하는 함수를 제공하자.
//  (P1, P2) -> R

// val csum2 = sum2.curried()

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

// 부분 적용
/*
fun main() {
    sum2(10, 30)
    sum2(10, 50)
    sum2(10, 60)

    // 커링을 사용하면 인자를 고정하는 것이 가능하다.
    // => 함수를 설계할 때, 인자의 변동 가능성이 낮은 순서대로 배치하는 것이 좋다.
    val plus10 = ::sum2.curried()(10)
    val plus3_2 = ::sum3.curried()(3)(2)

    println(plus10(30))
    println(plus10(20))

    println(plus3_2(10))  // 15

    val csum2 = ::sum2.curried()
    var result = csum2(10)(20)
    println(result)

    val csum3 = ::sum3.curried()
    result = csum3(10)(20)(30)
    println(result)
}
*/

enum class Level { INFO, WARN, ERROR }

// 로깅 라이브러리
fun log(level: Level, appendable: Appendable, message: String) {
    appendable.appendLine("[${level.name}]:[${LocalDateTime.now()}]: $message")
}

//-------------------
fun compute(logger: ((String) -> Unit)? = null) {
    logger?.invoke("Compute 시작")
    logger?.invoke("Compute 수행 중")
    logger?.invoke("Compute 완료")
}

fun main() {
    // 1. 람다 표현식
    compute { message ->
        log(Level.INFO, System.out, message)
    }

    println("----------")

    // 2. 커링
    val infoLogger = ::log.curried()(Level.INFO)(System.out)
    compute(infoLogger)

    infoLogger("프로그램 종료")
}







