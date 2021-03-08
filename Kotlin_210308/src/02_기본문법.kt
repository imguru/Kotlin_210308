// 02_기본문법.kt
package ex2

import java.util.*

// 1. main 함수를 만드는 방법
// 2. 함수를 만드는 방법
//   1) 전역함수를 만드는 것이 가능합니다.
//      Java - Arrays / Objects / Collections
//             정적 메소드를 모아놓은 클래스
//      Kotlin - 별도의 파일에 전역 함수를 모아놓으면 됩니다.

//   2) fun 함수이름(파라미터이름: 파라미터타입): 반환타입
//   3) Kotlin - OOP + FP(Functional Programming)
//      => 순수 함수(Pure Function)
//       : 함수의 입력값이 동일하면, 결과값이 동일하다.
//      => 불변 객체(Immutable Object)
//       : 객체 생성 이후로 값이 변경되지 않는 객체

// 함수가 결과값이 존재하지 않는 경우
// Java: void
// Kotlin: Unit
// fun print(): Unit {
fun print() {

}

fun add(a: Int, b: Int): Int {
    return a + b
}


// 3. 타입 시스템
//   Java
//     1) Primitive Type
//         int, double, char, byte ...
//        => value(복사)
//        - Collection에 저장할 수 없습니다.
//          ArrayList<int>       // X
//          ArrayList<Integer>
//        - 객체가 아니기 때문에, 필드와 메소드를 만들 수 없다.
//     2) Reference Type
//         class / enum / interface / array
//         String, Integer ...

//   Kotlin
//      1) 모든 것은 객체 타입입니다.
//         Int - 코틀린 컴파일러가 바이트 코드를 만들 때 판단합니다.
//      2) 강력한 타입 제약 언어 입니다.
//         - 암묵적인 타입 변환을 거의 허용하지 않습니다.
//         long / int

//  4. 변수 선언 방법(타입 추론)
//      - var: 변수 선언
//        var n: Int = 42  // Kotlin
//        var n = 42

//        int n = 42       // Java

//      - val: 상수 선언
//        val n = 42

//        final int n = 42   // Java

// toString
/*
fun main() {
    // println(print())
    // 42.toLong()
    val n1 = 42
    val n2: Long = n1.toLong()

    println(n2)
}
*/

// 5. 클래스 문법
/*
// User.java
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
*/
// - 여러 개의 public 클래스를 하나의 파일에 작성하는 것이 가능합니다.
// - public 이 기본입니다.
// - constructor 키워드를 통해 생성자 함수를 정의할 수 있습니다.
/*
public class User {
    private var name: String
    private var age: Int

    public constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

// class User constructor(private var name: String, private var age: Int)
// - constructor 생략이 가능합니다.

// 차이점
// - Object     ->  Any
// - @Override  ->  override
// - Object     ->  Any? (Nullable)

class User(private var name: String, private var age: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        // Any?
        if (other === null) {
            return false
        }
        // Any? -> Any

        // if (other !is User) {
        //    return false
        // }
        // Any -> User
        if (other.javaClass !== User::class.java) {
            return false
        }

        // 명시적인 캐스트
        other as User

        // Smart Cast: 컴파일러가 코드를 통해 타입을 자동으로 캐스팅해준다.
        return age == other.age && name == other.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}

fun main() {
    // final User user = new User("Tom", 42);

    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)
    // val user2 = user1

    // 동등성 판단
    //  1. 참조 동등성
    //    user1 == user2      // Java
    //    user1 === user2     // Kotlin

    //  2. 객체 동등성
    //    user1.equals(user2) // Java
    //    user1 == user2      // Kotlin

    if (user1 === user2) {
        println("같은 참조값")
    } else {
        println("다른 참조값")
    }

    if (user1 == user2) {
        println("같은 내용")
    } else {
        println("다른 내용")
    }


}






