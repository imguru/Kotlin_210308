// 22_메모이제이션2.kt
package ex22

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import java.util.*

// Gson - JSON Serialization
// hashCode / equals => Map에서 이용할 수 있습니다.
data class User(val nameAddress: String, val age: Int)
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
    println("T.toJson()")

    val gson = GsonBuilder()
        // .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
        .setPrettyPrinting()
        .create()

    return gson.toJson(this)
}

// WeakHashMap

// val user = User()
// val user = WeakReference<User>()       // GC가 발생할 때 수거됩니다.

// 기존 함수에 메모이제이션을 추가하는 함수
// => 재귀 함수에 대해서는 사용이 불가능합니다.
fun <A, B> ((A) -> B).memoized(): (A) -> B {
    val cache = mutableMapOf<A, B>()
    // val cache = WeakHashMap<A, B>()

    return { k ->
        cache.getOrPut(k) {
            this(k)
        }
    }
}

// OCP(Open - Close - Principle)
// : 개방 폐쇄의 원칙
//  => 수정에는 닫혀 있고, 확장에는 열려 있어야 한다.
//  => 새로운 기능이 추가되어도 기존 코드는 수정되면 안된다.

fun main() {
    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)

    // val toJsonCar = Car::toJson.memoized()
    // println(toJsonCar(Car("BMW", 100, 200)))
    // println(toJsonCar(Car("BMW", 100, 200)))

    val toJson = Any::toJson.memoized()
    println(toJson(user1))
    println(toJson(user2))

//    println(toJson(Car("BMW", 100, 200)))
//    println(toJson(Car("BMW", 100, 200)))

     // println(user1.toJson())
     // println(user2.toJson())
}