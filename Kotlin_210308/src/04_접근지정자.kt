// 04_접근지정자.kt
package ex4

// Java
//   private - package - protected - public
//  - protected: 같은 패키지 내에서 쉽게 접근이 가능하다.
//  - package: 다른 모듈에서 같은 이름의 패키지를 사용하면, 쉽게 접근이 가능하다.

// Kotlin
//   private - internal - protected - public
//  - protected: 더 이상 같은 패키지 내에서 접근이 불가능합니다.


// Android Studio
//  - Project
//    - Module1
//    - Module2

class Car {
    protected var name: String = "Car"
    internal var age: Int = 10
}

fun main() {
    val car = Car()
    // car.name = "Bob"
}