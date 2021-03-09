
// 11_위임2.kt
// 프로퍼티 위임: 프로퍼티에 대한 getter / setter에 대한 호출을 다른 객체에게 위임한다.

package ex11_2

import kotlin.reflect.KProperty

class SampleDelegate {
    // Getter
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        println("getValue")
        return "Bob"
    }

    // Setter
    operator fun setValue(thisRef: User, property: KProperty<*>, value: String) {
        println("setValue - $value")
    }
}

class User {
    var name: String by SampleDelegate()
}

fun main() {
    val user = User()
    user.name = "Tom"
    println(user.name)
}