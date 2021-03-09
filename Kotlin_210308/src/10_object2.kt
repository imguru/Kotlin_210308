// 10_object2
package ex10_2

// 2. Companion object(동반 객체)
// => Kotlin 에서는 static 키워드가 존재하지 않습니다.
/*
class User {
    companion object {
        // static field / method
        var name: String = "Tom"

        fun print() {
            println("User(name=$name)")
        }
    }
}

fun main() {
    println(User.name)
    User.print()
}
*/

// 활용 1. 정적 팩토리 메소드
//    => 객체 생성에 대한 가독성을 높이는 목적으로 사용한다.
//       객체 생성에 대한 정책을 쉽게 변경할 수 있다.
class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String): User {
            return User(email.substringBefore("@"))
        }

        fun newFacebookUser(accountId: Int): User {
            return User("facebook@$accountId")
        }
    }
}

fun main() {
    // val user = User("hello@gmail.com")
    // val user = User(1234556)

    val user1 = User.newSubscribingUser("hello@gmail.com")
    val user2 = User.newFacebookUser(1234556)
}










