package ex11

// Delegation
//  : 하나 이상의 메소드 호출을 다른 객체에게 위임한다.

// 1. 클래스 위임
interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// class Panel
//   OOP
//     1. 상속
//       => 부모의 모든 기능을 암묵적으로 이용할 수 있다.
// class Panel(x1: Int, x2: Int, y1: Int, y2: Int) : Rectangle(x1, x2, y1, y2)
//   "상속은 목적은 다형성이다."

//    2. 포함
//       => 느슨한 결합
//          실행시간에 의존하는 객체를 변경하는 것이 가능하다.
class Panel(rectangle: UIElement) : UIElement by rectangle

/*
class Panel(val rectangle: UIElement) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

fun main() {
     val panel = Panel(Rectangle(10, 20, 30, 40))
     println(panel.getWidth())
     println(panel.getHeight())
}

//     2. 포함

