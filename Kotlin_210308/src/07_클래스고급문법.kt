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
class Button : Clickable, Focusable {

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
}

