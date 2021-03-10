// 19_inline.kt
package ex19

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

// Kotlin 에서는 synchronized 키워드가 제공되지 않습니다.

class IncThread(val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // Kotlin - Range
        for (i in 1..1_000_000) {
            lock.lock()
            n += 1
            lock.unlock()
        }
    }
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