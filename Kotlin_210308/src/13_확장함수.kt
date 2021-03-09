// 13_확장함수.kt
package ex13

import java.lang.StringBuilder

/*
class User {
    // (Int, Int) -> Unit
    //  => (User, Int, Int) -> Unit
    fun move(x: Int, y: Int) {
        println("User move")
    }

    // () -> Unit
    // => (User) -> Unit
    fun print() {
    }
}

fun main() {
    val fn1: (User, Int, Int) -> Unit = User::move
    val fn2: (User) -> Unit = User::print

    val user = User()
    // user.move(10, 20)  // move(user, 10, 20)

    fn1(user, 10, 20)
    fn2(user)

    // 첫번째 인자가 객체로 결정되었습니다. - 바인드 된 참조(bound reference)
    val fn3: (Int, Int) -> Unit = user::move
    fn3(10, 20) // user.fn3(10, 20)
}
*/

// Extension Function - 확장 함수
// => 코틀린 라이브러리를 구현하는 핵심 기술

// 기존 라이브러리를 확장하는 방법
//  => 상속
//    : 기반 클래스를 상속 받아서, 라이브러리를 확장해 사용한다.
//    한계 - 기존 클래스가 상속을 목적으로 설계되어 있지 않다면, 확장이 불가능하다.
//    - 수직 확장

// => 확장 함수
//    : 사용자가 정의한 함수가 기존 클래스의 메소드처럼 보이게 하는 기술
//    - 수평 확장

// (String) -> Char
// fun lastChar(text: String): Char = text[text.length - 1]

// (String) -> Char
fun String.lastChar(): Char = this[length - 1]

fun Int.foo() = println("Int foo")

// String. - 수신 객체 타입
// this    - 수신 객체 참조
/*
fun main() {
    42.foo()
    val str: String = "hello"
    // val result = lastChar(str)

    val result = str.lastChar()

    // lastChar(str) -> str.lastChar()

    println(result)
}
*/

// Collection
//  list - [ 1, 2, 3 ]  =>  "[1, 2, 3]"

// joinToString(col, ",", "[", "]")

/*
fun <T> joinToString(
    collection: Collection<T>,
    seperator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(seperator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}
*/

fun <T> Collection<T>.joinToString(
    seperator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in withIndex()) {
        if (index > 0)
            result.append(seperator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(10, 20, 30)
//    val result = joinToString(
//        list,
//        seperator = ",",
//        prefix = "[",
//        postfix = "]"
//    )

    val result = list.joinToString(
        seperator = ",",
        prefix = "[",
        postfix = "]"
    )

    println(result)
}






