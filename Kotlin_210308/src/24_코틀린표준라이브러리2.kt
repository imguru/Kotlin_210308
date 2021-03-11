// 24_코틀린표준라이브러리2.kt
package ex24

// 'Collection'을 다루는 많은 표준적인 연산을 지원하고 있습니다.
//  * Kotlin - Sequence API
//  * Java   - Stream API(Java 8) - Android minimum SDK 26

/*
fun main() {
    // val list = listOf(1, 2, 3, 4, 5, 6)
    // val list = emptyList<Int>()
    val list = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)

    // 첫번째 원소
    // val result1 = list[0]         // list.get(0)
    // println(result1)

    // 예외   /  Nullable
    // - first / firstOrNull
    // - last  / lastOrNull

    // val result1 = list.first()
    val result1 = list.firstOrNull() ?: 0
    println(result1)
    // result1?.let {
    //    println(result1)
    // }

    // - take: 데이터 추출 연산
    // - drop: 요소를 제거한 결과를 받을 때
    // val result2 = list.take(8)
    val result2 = list.takeIf {
       it.count() > 10
    }
    println(result2)

    val result3 = list.drop(2)
    println(result3)

    // - distinct: 중복된 요소를 제거한 리스트를 얻을 때
    val result4 = list.distinct()
    println(result4)

    var i = 0
    val result5 = list.distinctBy { e ->
        e + 10 + i++
    }
    println(result5)
}
*/

data class User(val name: String, val age: Int) {

    // fun print(User)
    fun print() {
        println("name = $name")
    }
}

class Person {
    // Person::print
    // fun print(Person, User)

    // val person = Person()
    // person::print          - bound reference
    // fun print(User)
    fun print(user: User) {
        println("name = ${user.name}")
    }
}

fun main() {
    val list = listOf(
        User("Tom1", 41),
        User("Tom2", 42),
        User("Tom3", 43),
        User("Tom4", 44),
        User("Tom5", 45),
        User("Tom6", 46),
    )

    // forEach - 순회
    list.forEach {
        println(it)
    }

    // Java 8 - Method Reference
    list.forEach(::println)

    list.forEach(User::print)

    val person = Person()
    list.forEach {
        person.print(it)
    }

    list.forEach(person::print)
}








