package com.lge.ex16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sample {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);

        List<Integer> arr2 = Collections.unmodifiableList(arr);
        // arr2.add(10);

    }
}
