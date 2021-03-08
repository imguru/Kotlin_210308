// 02_기본문법.kt
package ex2

import com.lge.ex2.Sample

// 1. main 함수를 만드는 방법
// 2. 함수를 만드는 방법
//   1) 전역함수를 만드는 것이 가능합니다.
//      Java - Arrays / Objects / Collections
//             정적 메소드를 모아놓은 클래스
//      Kotlin - 별도의 파일에 전역 함수를 모아놓으면 됩니다.

//   2) fun 함수이름(파라미터이름: 파라미터타입): 반환타입
//   3) Kotlin - OOP + FP(Functional Programming)
//      => 순수 함수(Pure Function)
//       : 함수의 입력값이 동일하면, 결과값이 동일하다.

// 함수가 결과값이 존재하지 않는 경우
// Java: void
// Kotlin: Unit
// fun print(): Unit {
fun print() {

}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun main(args: Array<String>) {
    println(print())
}



