// 16_고차함수.kt
package ex16_3

// 함수를 이용해서 정책을 전달하는 방법
//  => Java를 제외한 대부분의 언어가 사용하는 방식

// 정책 함수
fun isEven(e: Int) = e % 2 == 0
fun isOdd(e: Int) = e % 2 == 1
// (Int) -> Boolean

fun<E> filter(data: List<E>, test: (E) -> Boolean): List<E> {
    val result = mutableListOf<E>()

    for (e in data) {
        if (test(e)) {
            result.add(e)
        }
    }

    return result
}


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filter(list, ::isEven)
    println(result)

    result = filter(list, ::isOdd)
    println(result)
}