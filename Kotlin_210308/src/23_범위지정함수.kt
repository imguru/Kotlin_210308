// 23_범위지정함수.kt
package ex23

import java.lang.StringBuilder

// 1. let
// 2. with / apply / also / run
// 3. use
//   - Try with resources

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\n")
    return result.toString()
}

inline fun <T, R> with1(receiver: T, block: (T) -> R): R {
    return block(receiver)
}

fun alphabet_with1(): String =
    with1(StringBuilder()) {
        for (letter in 'A'..'Z') {
            it.append(letter)
        }
        it.append("\n")
        return it.toString()
    }

inline fun <T, R> with2(receiver: T, block: T.() -> R): R {
    // return block(receiver)
    return receiver.block()
}

// with
//  => 수신 객체(this) 지정 람다 표현식
fun alphabet_with2(): String =
    with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\n")
        return toString()
    }


inline fun <T> T.apply1(block: T.() -> Unit): T {
    block()
    return this
}

// apply - Builder 초기화
fun alphabet_apply(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n")
}.toString()


inline fun buildString(builderAction: StringBuilder.() -> Unit): String {
    return StringBuilder().apply(builderAction).toString()
}

// apply - buildString
//  => Builder 를 생성하는 로직에 사용하는 경우가 많습니다.
fun alaphabet_buildString(): String = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n")
}

class Address(val name: String, val code: Int)
class User(val name: String, val address: Address)

// apply
inline fun <T> T.apply2(block: T.() -> Unit): T {
    block()  // this.block()
    return this
}

// also
inline fun <T> T.also2(block: (T) -> Unit): T {
    block(this)
    return this
}

// run
inline fun <R> run(block: () -> R): R {
    return block()
}

inline fun <T, R> T.run1(block: T.() -> R): R {
    return block()
}

class Resource : AutoCloseable {
    override fun close() {
        println("Resource close")
    }
}

fun main() {

    val user = User("Tom", Address("Suwon", 16512))
    val name = user.run {
        user.name
    }

    val longNameUser = user.takeIf {
        it.name.length > 5
    }

    println(longNameUser)
    println(user.address.name)
    println(user.address.code)

    with(user.address) {
        println(name)
        println(code)
    }
}













