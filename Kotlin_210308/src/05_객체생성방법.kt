// 05_객체생성방법.kt

package ex5

// 아래의 코드는 자바에서 지원하지 않는 문법을 사용하고 있습니다.
/*
class User(
    var name: String,
    var address: String,
    var age: Int = 0,
    var level: Int = 0,
)
*/

class User @JvmOverloads constructor(
    var name: String,
    var address: String,
    var age: Int = 0,
    var level: Int = 0,
)

// Kotlin 에서는 더 이상 자바처럼 Builder를 사용하지 않습니다.
// => Parameter Label 지정 호출을 이용하면 됩니다.
//    기본 파라미터값

fun main() {
    val user = User(
        name = "Tom",
        address = "Suwon",
        // age = 10,
        // level = 20,
    )

}