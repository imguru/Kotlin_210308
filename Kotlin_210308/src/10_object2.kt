// 10_object2
package ex10_2

// 2. Companion object(동반 객체)
// => Kotlin 에서는 static 키워드가 존재하지 않습니다.
/*
class User {
    companion object {
        // static field / method
        var name: String = "Tom"

        fun print() {
            println("User(name=$name)")
        }
    }
}

fun main() {
    println(User.name)
    User.print()
}
*/

// 활용 1. 정적 팩토리 메소드
//    => 객체 생성에 대한 가독성을 높이는 목적으로 사용한다.
//       객체 생성에 대한 정책을 쉽게 변경할 수 있다.
class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String): User {
            return User(email.substringBefore("@"))
        }

        fun newFacebookUser(accountId: Int): User {
            return User("facebook@$accountId")
        }
    }
}

/*
fun main() {
    // val user = User("hello@gmail.com")
    // val user = User(1234556)

    val user1 = User.newSubscribingUser("hello@gmail.com")
    val user2 = User.newFacebookUser(1234556)
}
*/

// Map<String, Any> => JSON
// { "name": "Tom", "age": 42 }  =>  User(name="Tom", age=42)

interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

data class Person(val name: String, val age: Int) {
    // Kotlin: Person.fromMap(...)
    //   Java: Person.Companion.fromMap()

    // companion object에 이름 부여가 가능하다.
    // => Java에만 영향을 미친다.
    companion object C : MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            val age = map["age"] as Int

            return Person(name, age)
        }
    }
}

// Java: 동적 생성을 구현하기 위해서는 'Reflection'을 이용해야 합니다.
//  startActivity

// Kotlin은 companion object를 활용해서 구현하는 것이 가능합니다.
fun <T> loadFromMap(factory: MapFactory<T>): T {
    val map = mapOf(
        "name" to "Tom",
        "age" to 42,
    )

    return factory.fromMap(map)
}

fun main() {
    val person = loadFromMap(Person)
    println(person)
}








