package ex20

class Person(val name: String)

// 1. for-loop
fun lookForAlice1(people: List<Person>) {
    for (person in people) {
        if (person.name == "Tom")
            continue

        if (person.name == "Alice") {
            println("Found!!!")
            break
        }
    }

    println("Failed to find Alice")
}

// 2. for-loop => forEach
fun lookForAlice2(people: List<Person>) {
    people.forEach hello@{ person ->
        if (person.name == "Tom") {
            // return@forEach               // 지역 반환
            return@hello
        }

        if (person.name == "Alice") {
            println("Found!!!")
            return                       // 비 지역 반환을 합니다!
        }
    }

    println("Failed to find Alice")
}

// 익명 함수
fun lookForAlice3(people: List<Person>) {

    people.forEach(fun(person) {
        if (person.name == "Alice") {
            println("Found!!")
            // return                      // 지역 반환
            return@lookForAlice3           // 비지역 반환
        }
    })

    println("Failed to find Alice")
}

fun lookForAlice(people: List<Person>) {
    run {
        people.forEach { person ->
            if (person.name == "Alice") {
                println("Found!!!")
                return@run                   // break
            }
        }
    }

    println("Failed to find Alice")
}

fun main() {
    val list = listOf(
        Person("Tom"),
        Person("Bob"),
        Person("Alice"),
    )

    lookForAlice(list)
}