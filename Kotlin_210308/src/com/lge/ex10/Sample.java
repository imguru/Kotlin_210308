package com.lge.ex10;

// Singleton Pattern
//  의도: 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 접근할 수 있다.

// 문제점: 프로그램이 로드되는 시점에 객체 생성이 발생한다.
/*
class Cursor {
    private static final Cursor INSTANCE = new Cursor();

    private Cursor() {
    }

    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/

// 문제점: 스레드 안정성 문제
/*
class Cursor {
    private static Cursor sInstance;

    private Cursor() {
    }

    /*
    public static Cursor getInstance() {
        if (sInstance == null)
            sInstance = new Cursor();
        return sInstance;
    }

    // DCLP - Double Checked Locking Pattern
    // => 코드가 선언적이지 않다.
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }
        return sInstance;
    }
}
*/

// IODH(Initialization on Demand Holder) Singleton
//  1. static final 에 대한 생성은 스레드 안전하다.
//  2. 중첩 클래스의 필드는 처음 접근되는 시점에 생성된다.
class Cursor {
    static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    private Cursor() {}

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }

    public void move(int x, int y) {

    }
}

public class Sample {
    public static void main(String[] args) {
        Cursor c = Cursor.getInstance();
        c.move(10, 20);

    }
}
