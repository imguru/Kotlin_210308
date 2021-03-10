// 16_고차함수2.kt
package ex16_2

fun filterEven(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 0) {
            result.add(e)
        }
    }

    return result
}

fun filterOdd(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 1) {
            result.add(e)
        }
    }

    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filterEven(list)
    println(result)

    result = filterOdd(list)
    println(result)
}
