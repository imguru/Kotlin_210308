// 16_고차함수2.kt
package ex16_2

// 정책을 분리하는 방법 - 동작 파라미터화 설계
//  => 인터페이스 기반의 클래스를 통해 정책을 분리한다.
//  => 자바에서는 함수를 참조할 수 있는 기술을 제공하지 않았습니다.

interface Predicate<T> {
    fun test(e: T): Boolean
}

fun <E> filter(data: List<E>, predicate: Predicate<E>): List<E> {
    val result = mutableListOf<E>()

    for (e in data) {
        if (predicate.test(e)) {
            result.add(e)
        }
    }

    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filter(list, object : Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 0
    })
    println(result)

    result = filter(list, object : Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 1
    })

    println(result)
}
