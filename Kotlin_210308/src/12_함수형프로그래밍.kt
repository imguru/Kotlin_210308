// 12_함수형프로그래밍.kt
// => Functional Programming

//  * 함수형 언어
// => Haskell / Lisp / Erlang / Scala

//  * 함수형 언어
//    1) 함수를 변수에 담을 수 있다.
//    2) 함수를 인자로 전달할 수 있다.
//    3) 함수를 반환할 수 있다.
//    4) 실행 시간에 함수를 생성할 수 있다.
//    5) 익명으로 함수를 생성할 수 있다.
//   => 함수를 '일급 시민(First class citizen)'으로 취급한다.

package ex12

// 1. 단일 표현식 함수
//   => 간단한 한줄 정도의 함수를 간결하게 표현할 수 있습니다.

fun add1(a: Int, b: Int): Int {
    return a + b
}

// 타입 추론이 가능합니다.
//  - 반환 타입 생략이 가능합니다.
// fun add2(a: Int, b: Int): Int = a + b
fun add2(a: Int, b: Int) = a + b
// val a: Int = 10

// 2. 함수의 타입
//   => 함수의 타입은 함수의 시그니처에 의해 결정됩니다.
//      함수의 인자의 타입과 개수 / 반환타입 => 함수의 시그니처

// ::add1 -> 전역 함수

// KFunction2 - JVM 내부의 타입입니다.
//  => KFunction{ArgN}<ArgT1, ArgT2, RetT>

// (Char, Double) -> String
fun foo(a: Char, b: Double): String {
    return ""
}

fun main() {
    // var a: Int = 42

    // var fn1: KFunction2<Int, Int, Int> = ::add1
    var fn1: (Int, Int) -> Int = ::add1
    fn1 = ::add2

    // fn1 = ::add2
}











