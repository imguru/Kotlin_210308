// 24_코틀린표준라이브러리2.kt
package ex24

// 'Collection'을 다루는 많은 표준적인 연산을 지원하고 있습니다.
//  * Kotlin - Sequence API
//  * Java   - Stream API(Java 8) - Android minimum SDK 26


fun main() {
    // val list = listOf(1, 2, 3, 4, 5, 6)
    val list = emptyList<Int>()

    // 첫번째 원소
    // val result1 = list[0]         // list.get(0)
    // println(result1)


    // 예외   /  Nullable
    // first / firstOrNull
    // last  / lastOrNull

    // val result1 = list.first()
    val result1 = list.firstOrNull() ?: 0
    println(result1)
    // result1?.let {
    //    println(result1)
    // }

}