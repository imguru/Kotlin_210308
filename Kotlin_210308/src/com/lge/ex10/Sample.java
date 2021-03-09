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
//class Cursor {
//    static class Singleton {
//        private static final Cursor INSTANCE = new Cursor();
//    }
//
//    private Cursor() {}
//
//    public static Cursor getInstance() {
//        return Singleton.INSTANCE;
//    }
//
//    public void move(int x, int y) {
//
//    }
//}

import ex10.Cursor;
import ex10_2.Person;

import java.lang.reflect.Constructor;

// 모든 클래스는 클래스의 내부 정보를 담고 있는 class가 있습니다.
// => 클래스의 클래스

class Point {
    private Point() {
    }
}

class Car {

}

class Truck extends Car {
    void go() {
        System.out.println("Truck go");
    }
}

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

        /*
           Intent intent = new Intent(this, MainActivity.class);
           startActivity(intent);

           // Retrofit
           // interface UserApi {
           //   @GET("/")
           //   Call<User> getUser();
           // }

           // UserApi api = new Retrofit.Builder().create(UserApi.class);

    }


    /*
    public static void main(String[] args) {
        // Cursor c = Cursor.getInstance();
        // c.move(10, 20);

        // Object 선언으로 만들어진 객체를 접근하는 방법
        Cursor.INSTANCE.move(10, 20);
    }

}
*/

interface MouseAdapter {
    void mouseClicked();

    void mouseEntered();
}

class Window {
    private MouseAdapter adapter;

    public void setAdapter(MouseAdapter adapter) {
        this.adapter = adapter;
    }

    public void click() {
        if (adapter != null) {
            adapter.mouseClicked();
        }

        adapter.mouseClicked();
    }

    public void enter() {
        if (adapter != null) {
            adapter.mouseEntered();
        }
    }

    //...
}


public class Sample {
    public static void main(String[] args) {
        Window window = new Window();
        window.setAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked() {
                System.out.println("mouseClicked");
            }

            @Override
            public void mouseEntered() {
                System.out.println("mouseEntered");
            }
        });
    }
}









