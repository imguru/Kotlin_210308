package com.lge.ex9;

/*
// Mark-up Interface
//  : 내부에 메소드가 존재하지 않고, 타입 체크 목적으로 사용하는 인터페이스
public interface Cloneable {
}
*/


// Object
//   |
//  Point
//   |
//  Rect - clone()

// Effective Java
// => clone을 도입하기 보다는, 복사 생성자를 사용하는 것이 좋다.
//    복사 생성자 - 자신과 동일한 타입을 인자로 받는 생성자

// => finalize
//    - 객체가 더 이상 사용되지 않아서, GC에 의해 파괴될 때 호출되는 함수
//    1) 자식 클래스가 finalize를 오버라이딩 하면, 부모의 finalize가 호출되지 않는다.
//    2) finalize 호출 시점이 명확하지 않습니다.
//    3) finalize가 호출되는 것이 보장되지 않는다.

class Point implements Cloneable {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(Point rhs) {
        this.x = rhs.x;
        this.y = rhs.y;
    }

    // 1. Object.clone 오버라이딩
    // 2. protected -> public
    // 3. 예외처리 - 메소드 내부로 변경
    // 4. Object 반환 타입을 구체적인 타입으로 변경 - 공변 반환의 룰
    // 5. 메소드 내부에서 캐스팅
    // 6. Cloneable 인터페이스를 구현해야 합니다.

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


public class Sample {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        // Point p2 = p1.clone();
        Point p2 = new Point(p1);

        System.out.println(p2);
    }
}
