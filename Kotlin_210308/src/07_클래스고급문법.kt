// 07_클래스고급문법.kt
package ex7

// Interface
//   => Interface는 구현을 가지지 않습니다. => 기본 구현을 가질 수 있습니다.
//      'Interface'에 새로운 기능을 추가하는 것이 어렵다.

//   =>  Java8 부터는 인터페이스에 기본 구현을 제공할 수 있습니다.
//       default method / defender method

interface Clickable {
    fun click()

    fun showOff() {
        println("Clickable")
    }
}

interface Focusable {
    fun focus()

    fun showOff() {
        println("Focusable")
    }
}

// class Button implements Clickable implements Focusable
open class Button : Clickable, Focusable {
    // 다이아몬드 상속 이슈 - 상위 인터페이스가 동일한 메소드 기본 구현을 제공할 경우, 반드시 재정의가 필요합니다.
    override fun showOff() {
        super<Focusable>.showOff()
        super<Clickable>.showOff()
    }

    override fun click() {
        println("Button click")
    }

    override fun focus() {
        println("Button focus")
    }

    // 코틀린의 기본 메소드는 오버라이딩이 금지되어 있습니다.
    // Java: final void move(int x, int y) {}

    // open fun: 오버라이딩 허용
    // open class: 상속 허용
    open fun move(x: Int, y: Int) {
        val open = 42
    }
}

// 상속 받는 방법
// class MouseButton extends Button

//  - Kotlin에서 class를 만들면, 기본은 상속이 금지되어 있습니다.
//    Java: public final class XXX - 상속 금지
//    Kotlin: class XXX

//    Java:   public class XXX     - 상속 허용
//    Kotlin: open class XXX

// 상속의 문제점
//  - 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 변경될 수 있다.
//  - 상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지해라 => Effective Java

class MouseButton : Button() {
    override fun move(x: Int, y: Int) {

    }
}








