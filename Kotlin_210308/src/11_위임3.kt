// 11_위임3.kt

package ex11_3

import java.util.concurrent.TimeUnit

// 코틀린이 이미 제공하는 표준 프로퍼티 위임 객체가 존재합니다.
// 1. lazy
//   => 프로퍼티 지연 초기화
//   => 프로퍼티가 처음 접근되는 시점에 생성되도록 하고 싶다.

class HeavyObject {
    init {
        println("HeavyObject creating!!")
        TimeUnit.SECONDS.sleep(2)
        println("HeavyObject created")
    }

    fun play() {}
}

class User {
    // val heavyObject:HeavyObject = HeavyObject()

    val heavyObject:HeavyObject by lazy {
        HeavyObject()
    }

    fun play() {
        heavyObject.play()
    }
}

fun main() {
    val user = User()
    println("User object created!")

    user.play()
}