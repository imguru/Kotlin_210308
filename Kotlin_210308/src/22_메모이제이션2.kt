// 22_메모이제이션2.kt
package ex22

import com.google.gson.GsonBuilder

// Gson - JSON Serialization
data class User(val name: String, val age: Int)
data class Car(val name: String, val color: Int, val speed: Int)

// JSON Serialization
/*
fun User.toJson(): String {
    println("User.toJson()")

    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    return gson.toJson(this)
}
*/
fun <T> T.toJson(): String {
    println("User.toJson()")

    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    return gson.toJson(this)
}

// 기존 함수에 메모이제이션을 추가하는 함수
fun <A, B> ((A) -> B).memoized(): (A) -> B {
    val cache = mutableMapOf<A, B>()
    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}

fun main() {
    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)

    val toJsonCar = Car::toJson.memoized()
    println(toJsonCar(Car("BMW", 100, 200)))
    println(toJsonCar(Car("BMW", 100, 200)))

    val toJson = User::toJson.memoized()
    println(toJson(user1))
    println(toJson(user2))

    // println(user1.toJson())
    // println(user2.toJson())
}