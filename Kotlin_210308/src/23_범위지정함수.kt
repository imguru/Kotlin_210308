// 23_범위지정함수.kt
package ex23

import java.lang.StringBuilder
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// 1. let
// 2. with / apply / use

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

// apply
fun alphabet_apply(): String = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n")
}.toString()

fun main() {

}













