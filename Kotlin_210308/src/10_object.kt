// 10_object.kt
package ex10

import java.io.File
import java.util.Comparator


//  1. object declaration
//   => Singleton을 생성할 때 사용하는 문법

object Cursor {
    var name: String = "Tom"

    init {
        name = "Bob"
    }
    // constructor 사용이 불가능합니다.

    fun move(x: Int, y: Int) {
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

fun main() {
    Cursor.move(10, 20)

    val files = listOf(File("/Z"), File("/a"))
    val result = files.sortedWith(CaseInsensitiveFileComparator)

    println(result)
}