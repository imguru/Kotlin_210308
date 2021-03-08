package com.lge.ex4;

class User {
    protected String name;

    // package level
    int age;
}

public class Sample {
    public static void main(String[] args) {
        User user = new User();

        // 같은 패키지 내에서 protected 필드에 접근하는 것이 가능합니다.
        user.name = "Tom";
    }
}
