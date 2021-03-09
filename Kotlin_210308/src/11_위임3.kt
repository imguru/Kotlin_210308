// 11_위임3.kt

package ex11_3

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

// 코틀린이 이미 제공하는 표준 프로퍼티 위임 객체가 존재합니다.


class HeavyObject {
    init {
        println("HeavyObject creating!!")
        TimeUnit.SECONDS.sleep(2)
        println("HeavyObject created")
    }

    fun play() {}
}

// 1. lazy
//   => 프로퍼티 지연 초기화
//   => 프로퍼티가 처음 접근되는 시점에 생성되도록 하고 싶다.
//   => val에 대해서만 사용할 수 있습니다.
//   => var의 경우에는 lateinit var 를 사용하면 됩니다.
//   => 스레드 안정성을 보장합니다.

class User {
    // lateinit var a: Int
    //  - Int, Double, Long, Float 같이 자바에서 원시타입으로 취급되는 타입은 사용이 불가능합니다.

    lateinit var a: String
        // get() {}
        // set(value) {}
    // - setter / getter 재정의가 불가능합니다.

    // val heavyObject:HeavyObject = HeavyObject()

    val heavyObject:HeavyObject by lazy {
        HeavyObject()
    }

    fun play() {
        heavyObject.play()
    }
}
/*
fun main() {
    val user = User()
    println("User object created!")

    user.play()
}
*/

// 2. 프로퍼티의 값의 변경에 따라 수행되는 로직을 캡슐화하는 목적
//   => KVO(Key-Value Observation)
class TextView {

    // var text: String = ""
    var text: String by Delegates.observable("") { _, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }
}

fun main() {
    val tv = TextView()
    tv.text = "Tom"
    tv.text = "Bob"
}