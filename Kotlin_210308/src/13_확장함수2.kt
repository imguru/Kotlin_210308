package ex13_2

// 정적 바인딩: 컴파일러가 참조 변수의 타입을 보고 어떤 메소드를 호출할지 결정한다.
// 동적 바인딩: 실행시간에 참조하고 있는 객체의 타입을 보고 어떤 메소드를 호출할지 결정하는 것

// 확장 함수
// - 정적 바인딩을 한다.
//   static method 이기 때문에
// - 기존의 클래스와 동일한 이름의 확장함수는 절대 호출될 수 없다.

open class View {
    open fun showOff() = println("I'm View!!!!")        // 1
}

class Button : View() {
    // override fun showOff() = println("I'm Button")  // 2
}

fun View.showOff() = println("I'm View")            // 1
fun Button.showOff() = println("I'm Button")        // 2

fun main() {
    val button: View = Button()
    button.showOff()
}