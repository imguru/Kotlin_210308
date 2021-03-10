// 18_커링.kt
package ex18

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업

// sum(10, 20, 30, 40)
//  커링 => sum(10)(20)(30)(40) => 110 결과 발생

// => 함수 실행을 지연할 수 있다.
// => 함수의 인자에 대해서 '부분 적용' 을 사용할 때

// Scala
//  fun sum(x: Int)(y: Int)(z: Int) -> Int

// sum(10, 20)  => 30
fun sum2(x: Int, y: Int): Int = x + y

// sum(x)(y)  => 30
fun sum2(x: Int) : (Int) -> Int = { y: Int ->
    x + y
}

// sum(10, 20, 30)
fun sum3(x: Int, y: Int, z: Int) = x + y + z

// sum(10)(20)(30) => 60
fun sum3(x: Int) : (Int) -> (Int) -> Int = { y ->
    { z ->
        x + y + z
    }
}

fun main() {
    var result = sum2(10, 20)
    println(result)

    result = sum2(10)(20)
    println(result)

    result = sum3(10, 20, 30)
    println(result)

    result = sum3(10)(20)(30)
    println(result)
}




