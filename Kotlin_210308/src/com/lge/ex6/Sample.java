package com.lge.ex6;

import ex6.User;

interface Clickable {
    void click();

    // Defender method
    default void showOff() {
        System.out.println("Clickable");
    }
}

public class Sample {
    public static void main(String[] args) {
        // User user = new User("Tom", 100);
        // user.setName("Bob");
    }
}
