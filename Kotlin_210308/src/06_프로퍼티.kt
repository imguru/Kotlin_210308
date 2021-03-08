// 06_프로퍼티.kt
package ex6

// 프로퍼티(Property)
// => 접근자 메소드를 자동으로 생성하는 기술

/*
class User {
    private String name;
    private int age;

    //...
    // Getter / Setter => Accessor Method(접근자 메소드)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
*/

// Property
// - var: setter + getter
// - val: getter
// class User(var name: String, val age: Int)
class User {
    var name: String
        set(value) {
            field = value
            println("Setter - $value")
        }
        get() {
            println("Getter - name")
            return field
        }

    val age: Int
        get() {
            println("Getter - age")
            return field
        }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

fun main() {
    val user = User("Tom", 42)
    user.name = "Bob"  // setName("Bob")
    // user.age = 100     // setAge(100)

    println("${user.name} / ${user.age}")
    //        user.getName() / user.getAge()
}
















