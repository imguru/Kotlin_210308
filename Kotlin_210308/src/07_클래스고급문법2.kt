// 07_클래스고급문법2.kt
package ex7_2

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState()
}

class Button : View {
    // 기본이 Nested class 입니다.
    // class ButtonState : State
    inner class ButtonState : State

    override fun getCurrentState(): State {
        return ButtonState()
    }

    override fun restoreState() {
        // ...
    }
}

// Kotlin 에서는 Checked Exception 존재하지 않습니다.
fun main() {
    val button = Button()

    val fos = FileOutputStream("state2.out")
    val oos = ObjectOutputStream(fos)
    oos.writeObject(button.getCurrentState())
}


// Interface vs Abstract class
// - 내부에 필드를 가질 수 없다.

interface Car {
    // Car의 인터페이스를 구현하는 클래스는 반드시 아래 프로퍼티를 제공해야 합니다.
    var name: String
    // Getter / Setter

    // val age: Int
    // Getter
}

class Truck(override var name: String) : Car

class Sedan : Car {
    override var name: String
        get() {
            return "Sedan"
        }
        set(value) {

        }
}











