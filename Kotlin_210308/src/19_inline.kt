// 19_inline.kt
package ex19

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

// Kotlin 에서는 synchronized 키워드가 제공되지 않습니다.

// inline: 함수를 호출하는 것이 아니라, 바이트 코드를 치환하는 기술
//  - 함수를 인자로 받는 고차 함수에서만 사용할 수 있습니다.
inline fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class IncThread(val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    // withLock의 호출이 오버헤드가 된다.
    //  => 함수를 인자로 전달받는 함수에 대해서, inline 함수를 지원합니다.
    override fun run() {

        for (i in 1..1_000_000) {
            withLock(lock) {
                n += 1
            }
        }

    }


    /*
    override fun run() {
        // Kotlin - Range
        for (i in 1..1_000_000) {
            try {
                lock.lock()
                n += 1
            } finally {
                lock.unlock()
            }
        }
    }
    */

    // 임계영역에서 예외가 발생하면, 데드락의 위험성이 있습니다.
    /*
    override fun run() {
        // Kotlin - Range
        for (i in 1..1_000_000) {
            lock.lock()
            n += 1
            lock.unlock()
        }
    }
    */
}


fun main() {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}

/*
class IncThread : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // Kotlin - Range
        for (i in 1..1_000_000) {
            n += 1
        }
    }
}


fun main() {
    val t1 = IncThread()
    val t2 = IncThread()

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}
*/