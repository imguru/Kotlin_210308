// 11_위임2.kt
// 프로퍼티 위임: 프로퍼티에 대한 getter / setter에 대한 호출을 다른 객체에게 위임한다.

package ex11_2

import kotlin.reflect.KProperty

/*
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
*/

interface ValueChanged<T> {
    fun onChangedValue(old: T, new: T)
}

class SampleDelegate<T>(var field: T, val listener: ValueChanged<T>? = null) {
    // Getter
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return field
    }

    // Setter
    operator fun setValue(thisRef: User, property: KProperty<*>, value: T) {
        if (value == field) {
            return
        }

        val old = field
        field = value
        listener?.onChangedValue(old, value)
    }
}

// 프로퍼티의 값의 변경이 발생하면, 수행되어야 하는 로직을 캡슐화하고 싶다.
// 1. field 저장할 수 있도록
// 2. 값이 변경이 발생할 때마다 알림을 받고 싶다.

class User {
    var name: String by SampleDelegate("Bob", listener = object: ValueChanged<String> {
        override fun onChangedValue(old: String, new: String) {
            println("onChangedValue: $old -> $new")
        }
    })
    // var age: Int by SampleDelegate()
}

fun main() {
    val user = User()
    user.name = "Tom"
    println(user.name)
}