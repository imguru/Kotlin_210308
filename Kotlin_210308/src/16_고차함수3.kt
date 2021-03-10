// 16_고차함수.kt
package ex16_3

import com.lge.ex16.Button
import com.lge.ex16.OnClickListener as JOnClickListener
// 패키지의 이름 충돌이 발생하였을 경우, 위와 같이 다른 이름으로 사용이 가능합니다.

// 함수를 이용해서 정책을 전달하는 방법
//  => Java를 제외한 대부분의 언어가 사용하는 방식
fun <E> filter(data: List<E>, test: (E) -> Boolean): List<E> {
    val result = mutableListOf<E>()

    for (e in data) {
        if (test(e)) {
            result.add(e)
        }
    }

    return result
}

// (Int) -> Boolean
// 정책 함수
fun isEven(e: Int) = e % 2 == 0
fun isOdd(e: Int) = e % 2 == 1

fun subscribe(
    onNext: () -> Unit,
    onError: (Error) -> Unit,
    onComplete: () -> Unit
) {

}

//class Button {
//    var onClick: (() -> Unit)? = null
//
//    fun click() {
//        // onClick?() - Kotlin X
//        onClick?.invoke()
//    }
//}

// Kotlin -> JVM(Java)

// 1.3 까지는 허용되지 않습니다.
// 1.4 - 코틀린의 인터페이스에 대해서도 'SAM' 을 지원합니다.
//  : fun interface
fun interface OnClickListener {
    fun onClick()
}

class Intent(context: MainActivity, clazz: Class<MainActivity>)

class MainActivity {
    var onClickListener: OnClickListener? = null

    fun onCreate() {
        onClickListener = object : OnClickListener {
            override fun onClick() {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
            }
        }

        onClickListener = OnClickListener {
            println("xxx")
            val intent = Intent(this, MainActivity::class.java)
        }

        val button = Button()
        button.name = "xxx"

        button.onClickListener = object : JOnClickListener {
            override fun onClick() {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                // startActivity(intent)
            }
        }

        // SAM(Single Abstract Method) 지원
        // => Functional Interface
        //  : 자바의 Functional Interface를 코틀린에서 이용할 때 람다 표현식으로 사용할 수 있습니다.
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
        }

        button.onClickListener = JOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
        }

    }
}


fun main() {
//    val button = Button()
//    button.onClick = {
//        println("xxx")
//    }


    subscribe({

    }, {

    }) {

    }

    // 함수의 인자가 여러 개의 함수를 받을 경우, 파라미터 라벨 지정 호출이 좋습니다.
    subscribe(onNext = {

    }, onError = {

    }, onComplete = {

    })

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    var result = filter(list, ::isEven)
    println(result)

    result = filter(list, ::isOdd)
    println(result)

    // '익명 함수' 를 사용할 수 있습니다.
    result = filter(list, fun(e: Int): Boolean {
        return e % 2 == 0
    })
    println(result)

    result = filter(list, fun(e: Int): Boolean = e % 2 == 0)
    println(result)

    // 코틀린은 '람다 표현식'은 익명 함수와 별도로 존재합니다.
    //  Lambda Expression: 호출 가능한 코드 블록
    //  => 최종 결과값은 마지막 구문에 의해서 결정됩니다.

    result = filter(list, { e: Int ->
        e % 2 == 0
    })

    // 타입 추론이 가능합니다.
    result = filter(list, { e ->
        e % 2 == 0
    })

    // 호출하는 함수의 마지막 인자가 함수일 경우, 람다 표현식의 블록을 호출 괄호 밖으로 이동 가능합니다.
    // => Trailing lambda
    result = filter(list) { e ->
        e % 2 == 0
    }

    // 람다의 인자가 1개 라면, 생략 가능합니다.
    //  it 을 통해 참조 가능합니다.
    result = filter(list) {
        it % 2 == 0
    }


}













