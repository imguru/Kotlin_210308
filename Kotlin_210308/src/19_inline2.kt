// 19_inline2.kt
package ex19_2

open class Car
class Truck : Car()

// Generic
//  1. C++ Template: 타입 인자로 전달된 T에 대한 코드를 컴파일 타임에 생성한다.
//  2. Java Generic: 컴파일 타임에 타입 체크를 목적으로 사용한다.
//                   JVM 상에서 사라진다. - 타입 소거형

/*
    List s = new ArrayList();
    s.add("hello");
    // T -> Object(Any)

    List<String> s = new ArrayList<String>();
    s.add("hello");
    s.add("xxx");

    // s.add(10);    // compile error!

*/

// 함수 호출을 하지 않고 인라인 치환을 하면 사용 가능하다.
inline fun <reified T> isA(value: Any) = value is T


open class Activity
class MainActivity : Activity()
class Intent(context: Any?, clazz: Class<out Activity>)

fun startActivity(intent: Intent) {}

inline fun <reified T : Activity> startActivity() {
    val intent = Intent(null, T::class.java)
    startActivity(intent)
}

fun main() {
    // val intent = Intent(null, MainActivity::class.java)
    // startActivity(intent)

    // val textView = findViewById<TextView>(R.id.textView)
    // TextView textView = (TextView)findViewId(r.id.textView)

    // Anko
    startActivity<MainActivity>()
}


/*
fun main() {
    val car1 = Car()
    val car2 = Truck()

    println(car1 is Car)
    println(car2 is Truck)

    println(isA<Truck>(car1))
    println(isA<Car>(car2))
}
*/