package ex20

class Person(val name: String)

// 1. for-loop
fun lookForAlice1(people: List<Person>) {
    for (person in people) {
        if (person.name == "Tom")
            continue

        if (person.name == "Alice") {
            println("Found!!!")
            return
        }
    }

    println("Failed to find Alice")
}

// 2. for-loop => forEach
fun lookForAlice(people: List<Person>) {
    people.forEach { person ->
        if (person.name == "Tom") {
            return@forEach               // 지역 반환
        }

        if (person.name == "Alice") {
            println("Found!!!")
            return                       // 비 지역 반환을 합니다!
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