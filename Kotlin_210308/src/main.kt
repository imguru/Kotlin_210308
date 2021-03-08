// main.kt
// 1) 코틀린 언어 특징
//   - 간결함
//    : 보일러플레이트를 제거합니다.
//   - 안전함
//    : Null을 안전하게 다루는 방법을 제공합니다.
//      - Nullable / Optional
//   - 상호운용성
//    : Java로 작성된 코드를 Kotlin에서 이용할 수 있고,
//      Kotlin에서 작성된 코드를 Java에서 이용할 수 있습니다.

package ex1
// Java 에서는 디렉토리 기반으로 작성해야지만, 코틀린에서는 제약사항이 사라졌습니다.
// => 지키는 것이 좋습니다.

/*
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}

$ javac Hello.java
// => Hello.class
$ java Hello
*/
/*
fun main(args: Array<String>) {
    println("Hello, Kotlin")
}
*/

// 1.3 버전부터
fun main() {
    println("Hello, Kotlin")
}

// $ kotlinc hello.kt
// => HelloKt.class

// $ kotlin HelloKt











