package com.lge.ex5;

/*
class User {
    private String name;
    private String address;
    private int age;
    private int level;

    public User(String name, String address, int age, int level) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.level = level;
    }

    public User(String name, String address) {
        this(name, address, 0, 0);
    }

    public User(String name, String address, int age) {
        this(name, address, age, 0);
    }
}
*/


// 2. Builder
//  => Boilerplate
//  => Lombok
/*
class User {
    private String name;
    private String address;
    private int age;
    private int level;

    private User(Builder b) {
        this.name = b.name;
        this.address = b.address;
        this.age = b.age;
        this.level = b.level;
    }

    // Nested Class
    public static class Builder {
        private String name;
        private String address;
        private int age;
        private int level;

        public Builder(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
*/


import ex5.User;

public class Sample {
    public static void main(String[] args) {
        String n = "Tom";
        String addr = "Suwon";
        int age = 42;
        int l = 10;

        // 1. Telescoping Constructor - Design Pattern X
        // User user1 = new User(n, addr, age, l);
        // User user2 = new User(n, addr, age);
        // User user3 = new User(n, addr);

        // 2. Builder Pattern - Design Pattern O
        // User user = new User.Builder(n, addr)
        //        .age(42)
        //        .level(10)
        //        .build();

        // User user = new User("Tom", "Suwon", 10, 10);
        User user = new User("Tom", "Suwon");

        // 이유: Kotlin의 문법이 Java에서 지원되지 않을 경우


    }
}
