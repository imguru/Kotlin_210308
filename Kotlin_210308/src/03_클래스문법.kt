// 03_클래스문법.kt
package ex3


class Car {
    fun go() {
        println("go")
    }

    fun go(speed: Int) {
        // "go - speed: " + speed
        println("go - speed: $speed")
    }

    fun go(speed: Int, destination: String) {
        println("go - speed: $speed, destination: $destination")
    }

    fun go(speed: Int, destination: String, color: Int) {
        println("go - speed: $speed, destination: $destination, color: $color")
    }
}

fun main() {
    val car = Car()
    val a = 100
    val b = "Suwon"
    val c = 0xff0000

    // 각 값이 어떤 파라미터로 전달되는지 알기 어렵다.
    car.go()
    car.go(a)
    car.go(a, b)
    car.go(a, b, c)

    // 파라미터 지정 호출
    car.go(speed = a)
    car.go(speed = a, destination = b)
    car.go(speed = a, destination = b, color = c)

    // 순서를 바꿔서 호출하는 것도 가능하지만, 지키는 것이 좋습니다.
    car.go(color = c, speed = a, destination = b)

    // Kotlin 1.4 버전부터 지원하는 기능
    //  - 부분 파라미터 지정이 가능하고, Trailing Comma 문법을 지원합니다.
    car.go(
        a,
        destination = b,
        c,
    )
}