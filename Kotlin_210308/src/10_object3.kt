
// 10_object3.kt
// 3. Anonymous object(익명 객체)
package ex10_3

/*
public class Sample {
    public static void foo(Car car) {
        // car가 Truck이라면 go 하고 싶다.
        if (car.getClass() == Truck.class) {
            Truck truck = (Truck) car;
            truck.go();
        }
    }

    public static void main(String[] args) throws Exception {
         // Person.Companion.fromMap()
         // Person.C.fromMap();
        foo(new Truck());
        foo(new Car());

        // 1. class
        Class clazz1 = Point.class;

        // Point p = new Point();
        // 2. object
        // Class clazz2 = p.getClass();

        // 3. 문자열 - 실패 가능성이 있습니다.
        Class clazz = Class.forName("com.lge.ex10.Point");

        // Reflection - 2가지 목적
        // 1) 정확한 타입 체크
        // if (clazz1 == clazz2) {
        //    System.out.println("동일한 타입입니다.");
        // }

        // 2) 동적 생성 - 클래스의 이름을 통해 객체를 생성하는 기술
        // Point p2 = (Point)clazz.newInstance();
        // System.out.println(p2);
        Constructor c = clazz.getDeclaredConstructor();
        c.setAccessible(true);
        Point p2 = (Point) c.newInstance();

        System.out.println(p2);

        / *
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);

           // Retrofit
           // interface UserApi {
           //   @GET("/")
           //   Call<User> getUser();
           // }

           // UserApi api = new Retrofit.Builder().create(UserApi.class);

    }


    / *
    public static void main(String[] args) {
        // Cursor c = Cursor.getInstance();
        // c.move(10, 20);

        // Object 선언으로 만들어진 객체를 접근하는 방법
        Cursor.INSTANCE.move(10, 20);
    }

}
*/

interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    var adapter: MouseAdapter? = null
    // 1. lateinit var
    //  => 객체가 생성된 이후로 초기화가 가능하지만, 예외 가능성이 있다.
    // lateinit var adapter: MouseAdapter

    fun click() {
        /*
         if (adapter != null) {
            adapter.mouseClicked()
            // var 이기 때문에, 스마트 캐스트가 불가능합니다.
         }
        */
        // val adapter = adapter
        // if (adapter != null) {
        //    adapter.mouseClicked()
        // }

        // 3. 강제 호출 - !!
        // => Null 참조에 의한 예외 가능성이 있습니다.
        adapter!!.mouseClicked()
    }

    fun enter() {
        // 2. Optional Chaining(Swift)
        adapter?.mouseEntered()
    }
}

fun main() {
    val window = Window()
    window.click()
    window.adapter = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouseClicked")
        }

        override fun mouseEntered() {
            println("mouseClicked")
        }
    }


    window.enter()
}










