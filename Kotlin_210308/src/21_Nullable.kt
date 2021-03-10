// 21_Nullable.kt
package ex21

import ex17.add

// Nullable
// => null 을 안전하게 다루는 방법
//   : Optional, Maybe
class User {
    fun sendEmail(email: String) {
        println("Send email to $email")
    }

    fun foo(email: String, address: String) {

    }
}

var email: String? = null
var address: String? = null

class Activity

var activity: Activity? = null

fun foo(activity: Activity) {}

// Fragment
//  onCreatedView
/*
fun main() {
    activity?.let {
        // ...
    }

    // Kotlin 에서는 '3항 연산자'가 존재하지 않습니다.
    // * 코틀린에서는 when expression 처럼 if(조건)도 expression 입니다.
    val name = if (activity == null)
        "Tom"
    else
        "Bob"

    var name2 = ""
    if (activity == null)
        name2 = "Tom"
    else
        name2 = "Bob"

    println(name)

    // * Elvis operator = null일 때 기본 동작을 정의하는 목적으로 사용할 수 있습니다.
    // val activity = activity ?: Activity()
    val activity = activity ?: return
    foo(activity)
}
*/


/*
fun main() {
    val user = User()
    email?.let { email: String ->
        address?.let { address: String ->
            user.foo(email, address)
        }
    }

    val email = email
    val address = address
    if (email != null && address != null) {
        user.foo(email, address)
    }
}
*/

class Country(val city: City?)
class City(val address: Address?)
class Address(val name: String)

open class Car
class Truck : Car()
class Sedan : Car() {
    fun go() {}
}

fun main() {
    val country = Country(City(Address("Suwon")))
    if (country.city != null) {
        if (country.city.address != null) {
            println(country.city.address.name)
        }
    }

    country.city?.address?.name?.let { name ->
        println(name)
    }

    val car: Car = Truck()
    val sedan: Sedan? = car as? Sedan
    sedan?.go()

    (car as? Sedan)?.go()


//    if (car is Sedan) {
//        val sedan = car as Sedan
//    }






    email = "hello@gmail.com"

    val user = User()
    // user.sendEmail(email)

    // 1. 강제 참조 - !!
    user.sendEmail(email!!)

    // 3. let - 범위 지정 함수
    email?.let { email ->
        user.sendEmail(email)
    }

    // 2. 별도 변수를 사용한다.
    val email = email
    if (email != null) {
        user.sendEmail(email)
    }

    val email2 = email ?: "support@gmail.com"
    user.sendEmail(email2)
}
