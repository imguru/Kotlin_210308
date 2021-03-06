// 09_데이터클래스.kt
package ex9


class User(val name: String, val age: Int) {

    // 비구조화 문법을 위한 약속된 함수
    operator fun component2(): String {
        return name
    }

    operator fun component1(): Int {
        return age
    }

    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }
}


// - VO / DAO / DTO
// data class User(val name: String, val age: Int)

// 프로퍼티가 반드시 1개 이상 제공되어야 합니다.
// data class Person

fun main() {
    val user = User("Tom", 42)

    // 1. 객체를 문자열로 표현하는 방법 - toString()
    println(user)


    // 2. 객체 동등성 비교 - equals / hashCode
    val other = User("Tom", 42)
    println(user == other)

    val users = listOf(user, other)
    for ((name, age) in users) {
        println("$name / $age")
    }

    // 3. 객체 복제 - clone
    // => Java의 clone은 더 이사 제공되지 않습니다.
    //  : 복사 생성자를 이용하는 것이 좋습니다.
    // => data class 에서는 copy 라는 메소드를 제공합니다.
//    val user2 = user.copy()
//    val user3 = user.copy(age = 100)     // "Tom" / 100
//    val user4 = user.copy(name = "Bob")  // "Bob" / 42
//
//    // 4. 비구조화 선언
//    val users = listOf(user, user2, user3, user4)
//    for (e in users) {
//        println("${e.name} / ${e.age}")
//    }
//
//    // 연산자 오버로딩
//    for ((name, age) in users) {
//        println("$name / $age")
//    }
}