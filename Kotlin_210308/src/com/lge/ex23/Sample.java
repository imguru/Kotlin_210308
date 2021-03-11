package com.lge.ex23;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


class MyResources implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("MyResource close");
    }

    public void go() {
        System.out.println("MyResource go");
    }
}


// - '비메모리 자원'에 대해서는 명시적인 종료 메소드를 호출해야 한다.
//  => Try with Resources
public class Sample {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(10, 20, 30, 40, 50);
        arr.forEach(e -> {
            System.out.println(e);
        });

        // Java's Method Reference
        arr.forEach(System.out::println);
        
        try (MyResources resources = new MyResources()) {
            resources.go();
        } catch (Exception e) {
            e.printStackTrace();
        }


        /*
        try (FileOutputStream fos = new FileOutputStream("a.txt");
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeChars("hello");
            dos.writeInt(42);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /*
    public static void main(String[] args) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("a.txt");
            dos = new DataOutputStream(fos);

            dos.writeChars("hello");
            dos.writeInt(42);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */

    /*
    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("a.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeChars("hello");
        dos.writeInt(42);
    }
    */
}
