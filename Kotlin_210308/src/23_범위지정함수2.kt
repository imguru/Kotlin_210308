// 23_범위지정함수2.kt

package ex23_2

import java.io.Closeable

class Resource : Closeable {
    override fun close() {
        println("Resource close")
    }

    fun foo() {
        println("Resource foo")
    }
}

// use - Java 7(Try with resources)
fun main() {
    val res1 = Resource()
    val res2 = Resource()
    res2.use {
        res1.use {
            it.foo()
        }
    }
    println("main end")
}