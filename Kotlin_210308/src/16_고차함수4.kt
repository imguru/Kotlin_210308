package ex16_4

// 고차 함수
// 1) 함수를 인자로 전달하는 함수
//  => 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.

// 2) 함수를 반환하는 함수
//  => 실행 시간에 함수를 생성하는 것이 가능합니다.
//  - 함수 합성
//  - 커링

fun foo(): (String) -> String {
    return { str:String ->
        str.reversed()
    }
}

fun goo(): (String) -> String = { str: String ->
    str.reversed()
}

// () -> ((String) -> String)
// => 결합의 순서가 오른쪽부터 결합됩니다.

/*
fun main() {
    val f: () -> (String) -> String = ::foo

    val fp: (String) -> String = f() // foo()

    val result = fp("hello")
    println(result)
}
*/

// fun isEven(e: Int) = e % 2 == 0
// fun isOdd(e: Int) = e % 2 == 1

fun modulo1(k: Int, r: Int): (Int) -> Boolean {
    return { e: Int ->
        e % k == r
    }
}

fun module2(k: Int, r: Int) = { e: Int ->
    e % k == r
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val isEven = modulo1(2, 0)
    val result = list.filter(isEven)

    println(result)
}






