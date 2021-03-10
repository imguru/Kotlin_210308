package com.lge.ex16;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
interface Predicate2 {
    void test(Integer e);

    default void foo() {

    }
}

public class Sample {
    static List<Integer> filter(List<Integer> data, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();

        for (Integer e : data) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("xxx");
            }
        });







        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int n = 10;
        List<Integer> result = filter(data, new Predicate<Integer>() {
            @Override
            public boolean test(Integer e) {
                System.out.println(n);
                return e % 2 == 0;
            }
        });
        for (Integer e : result) {
            System.out.println(e);
        }

        // Java8 에서는 인터페이스에 하나의 메소드만 존재하는 경우,
        // 익명 객체가 아닌 람다 표현식을 사용할 수 있습니다.
        // => Functional Interface
        result = filter(data, (Integer e) -> {
            System.out.println(n);
            return e % 2 == 0;
        });

        result = filter(data, (e) -> e % 2 == 0);

        for (Integer e : result) {
            System.out.println(e);
        }
    }

    /*
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);

        List<Integer> arr2 = Collections.unmodifiableList(arr);
        // arr2.add(10);
    }
    */
}
