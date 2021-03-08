package com.lge.ex2;

import java.util.Objects;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Object.equals

    @Override
    public boolean equals(Object other) {
        // 1. 참조 동등성 판단
        if (other == this) {
            return true;
        }

        // 2. null 체크
        if (other == null) {
            return false;
        }

        // 3. other가 User 타입인지 체크한다.
        // - User에 계층에 포함된 타입인지 체크한다.
        if (!(other instanceof User)) {
            return false;
        }

        // - 완전 동일한 타입인지 체크한다.
        // if (other.getClass() != User.class) {
        //    return false;
        // }

        // 4. 캐스팅이 필요합니다.
        User obj = (User) other;

        // 5. 객체의 동등성을 판단한다.
        //  필드의 구성요소가 원시타입인지 참조타입인지를 체크해야 한다.
        return age == obj.age && name.equals(obj.name);
    }

    // 객체의 동등성을 판단하는 equals를 재정의하면, 반드시 hashCode도 재정의가 필요합니다.

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}


public class Sample {
    public static void print() {
        System.out.println("Sample.print");
    }

    public static void main(String[] args) {
        // System.out.println(print());

        // int n = 42;
        // long l = n;

        // User u1 = new User("Tom", 42);
        User u1 = null;
        User u2 = new User("Tom", 44);

        // if (u1.equals(u2)) {
        if (Objects.equals(u1, u2)) {
            System.out.println("동일한 내용의 객체이다.");
        } else {
            System.out.println("동일한 내용의 객체가 아니다.");
        }


    }
}
