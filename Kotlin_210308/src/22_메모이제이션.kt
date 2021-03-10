package ex22

// 0, 1   -> 1
// 2 이상  -> fib(n-1) + fib(n-2)

// 메모이제이션: 동일한 인자를 갖는 함수의 결과를 캐시해서, 계산의 성능을 올리는 방법

/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/

val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> {
        val value = cache[k]
        if (value != null) {
            value
        } else {
            val result = fib(k - 1) + fib(k - 2)
            cache[k] = result
            result
        }
    }
}
*/

fun main() {
    val result = fib(50)
    println(result)
}