// 16_고차함수.kt
package ex16

// High order function
//  => 고차 함수
//   : '함수를 인자로 전달 받거나 함수를 반환하는 함수'





// *. 함수를 인자로 전달받는 함수
//  1) 코틀린의 Collection은 Java의 Collection과 다릅니다.

//              List<T>                :   Immutable
//                 |
//           MutableList<T>            :   mutable
//                 |
//     ArrayList<T> / LinkedList<T>

//    Set - MutableSet
//    Map - MutableMap


fun filterEven(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 0) {
            result.add(e)
        }
    }

    return result
}



fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

}