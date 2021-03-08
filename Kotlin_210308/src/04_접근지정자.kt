// 04_접근지정자.kt
package ex4

// Java
//   private - package - protected - public
//  - protected: 같은 패키지 내에서 쉽게 접근이 가능하다.
//  - package: 다른 모듈에서 같은 이름의 패키지를 사용하면, 쉽게 접근이 가능하다.

// Kotlin
//   private - internal - protected - public
//  - protected: 더 이상 같은 패키지 내에서 접근이 불가능합니다.
//  - internal: 다른 모듈에서 접근이 불가능합니다.
//     : package의 이름을 알수 없는 형태로 변경해서 사용한다.

// Android Studio
//  - Project
//    - Module1
//    - Module2

class Car {
    protected var name: String = "Car"
    internal var age: Int = 10
}

public class Foo
internal class Goo

// 같은 파일에서만 접근할 수 있습니다.
private class Hoo

// 전역 함수에서도 동일한 개념을 사용합니다.
public fun foo() {

}

internal fun goo() {

}

private fun hoo() {

}



fun main() {
    var h = Hoo()


    val car = Car()
    // car.name = "Bob"
}