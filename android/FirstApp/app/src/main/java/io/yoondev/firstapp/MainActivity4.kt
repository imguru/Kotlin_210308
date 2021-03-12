package io.yoondev.firstapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.yoondev.firstapp.databinding.ActivityMain2Binding


// Reactive eXtension
//  => 비동기 연산을 컬렉션의 다루는 것처럼 일반적인 방법을 통해 해결하고 싶다.

// Collection을 설계하는 기술
//  => Iterator Pattern
//   의도: 컬렉션의 내부 구조에 상관없이 요소를 열거하는 객체

//  Iterator(반복자)
//  Iterable
// => Collection 설계자는 제공해야 한다.


class SListIterator(private var current: SList.Node?) : Iterator<Int> {
    override fun hasNext(): Boolean = current != null

    // 현재의 current의 값을 반환하고, 다음 위치로 이동한다.
    override fun next(): Int {
        val ret = current?.value
        current = current?.next

        return ret!!
    }
}


class SList {
    class Node(val value: Int, val next: Node?)

    var head: Node? = null

    val front: Int?
        get() = head?.value

    fun addFront(value: Int) {
        head = Node(value, head)
    }
}


class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            val list = SList()
            list.addFront(10)
            list.addFront(20)
            list.addFront(30)

            Log.e("XXX", "${list.front}")
        }

    }

}