// 24_코틀린표준라이브러리.kt
package ex24

import java.io.File
import java.lang.IllegalStateException

// 1. 조건 확인 함수
// -   check: IllegalStateException
// - require: IllegalArgumentException
fun log(path: String, message: String) {
    val file = File(path)

//    if (!file.exists())
//        throw IllegalStateException("file not exist")

    check(file.exists())
    require(message.isNotBlank())
}

// 2. 함수가 절대로 반환하지 않는 경우, 반환 타입은 Nothing을 사용합니다.
fun foo(): Nothing {
    throw IllegalStateException()
}

// 3. 명시적인 실행 종료 함수
// - error: IllegalStateException
// - TODO: NotImplementedError
fun goo() {
    // TODO: xxx
    // error("문제가 발생하였다.")

    TODO("xxx")
}

fun main() {
    // log("/a", "hello")
    goo()
}