package ex16_4

// 고차 함수
// 1) 함수를 인자로 전달하는 함수
//  => 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.

// 2) 함수를 반환하는 함수
fun foo(): (String) -> String {
    return { str:String ->
        str.reversed()
    }
}

fun goo(): (String) -> String = { str: String ->
    str.reversed()
}

fun main() {
    val fp: (String) -> String = goo() // foo()

    val result = fp("hello")
    println(result)
}