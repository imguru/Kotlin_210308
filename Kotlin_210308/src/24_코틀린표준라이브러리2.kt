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

data class Sample(val name: String)

fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(
        User("Tom", 10),
        User("Tom", 20),
        User("Tom", 30),
        User("Tom", 40),
        User("Tom", 50),
        User("Bob", 10),
        User("Bob", 20),
        User("Bob", 30),
        User("Bob", 40),
        User("Bob", 50),
    )

    // zip
    //  두 개의 컬렉션을 통해 새로운 컬렉션을 생성한다.
    val result = list1.zip(list2) { a: Int, b: User ->
        "$a - $b"
    }

    // groupBy - 분류 작업
    // - List<User>   ->   groupBy  -> Map<String, List<User>>
    val result2 = list2.groupBy { user ->
        /*
        if (user.age in 10..19) {
            "10대"
        } else if (user.age in 20..29) {
            "20대"
        } else if (user.age in 30..39) {
            "30대"
        } else if (user.age in 40..49) {
            "40대"
        } else {
            "50대"
        }
        */

        /*
        when {
            user.age in 10..19 -> "10대"
            user.age in 20..29 -> "20대"
            user.age in 30..39 -> "30대"
            user.age in 40..49 -> "40대"
            else -> "50대"
        }
        */
        when (user.age) {
            in 10..19 -> "10대"
            in 20..29 -> "20대"
            in 30..39 -> "30대"
            in 40..49 -> "40대"
            else -> "50대"
        }
    }

    println(result2)

    println(result)
}


/*
fun main() {
    val list = listOf(
        User("Tom1", 41),
        User("Tom2", 42),
        User("Tom3", 43),
        User("Tom4", 44),
        User("Tom5", 45),
        User("Tom6", 46),
    )

    // map(transform) - 변환
    // filter

    // List<T> -> map -> List<U>

    /*
    val result: List<String> = list.map { user ->
        user.name  // String
    }
    */

    /*
    val result2 = list.map { user ->
        if (user.age > 43)
            Sample(user.name)
        else
            null
    }.filter {
        it != null
    }
    */
    /*
    val result2 = list.map { user ->
        if (user.age > 43)
            Sample(user.name)
        else
            null
    }.filterNotNull()
    */

    /*
    val result2 = list.mapNotNull { user ->
        if (user.age > 43)
            Sample(user.name)
        else
            null
    }

    println(result2)
    */

    // List<User> ->   map   ->  List<List<String>>
    // List<User> -> flatMap ->  List<String>
    val result: List<List<String>> = list.map { user ->
        listOf(user.name, "${user.age}")
    }
    println(result)

    // flatMap: 변환의 결과가 중첩될 경우, 1차원으로 변환해준다.
    val result2: List<String> = list.flatMap { user ->
        listOf(user.name, "${user.age}")
    }
    println(result2)


    // forEach - 순회 / forEachIndexed
    /*
    list.forEachIndexed { index, user ->
        println("${index}는 ${user}입니다")
    }

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
    */
}
*/







