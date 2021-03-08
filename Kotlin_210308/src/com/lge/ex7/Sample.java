package com.lge.ex7;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Java
//  - Inner class
//   주의: 객체가 생성되면, 외부 클래스 객체에 대한 참조가 존재한다.
//  => Iterator / Adapter
/*
class Button {
    class ButtonState {}
}
*/

//  - Nested class
/*
class Button {
    static class ButtonState {}
}
*/

interface State extends Serializable {
}

interface View {
    State getCurrentState();

    void restoreState(State state);
}

class Button implements View {
    static class ButtonState implements State {
    }

    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(State state) {
        // ...
    }


}


public class Sample {
    public static void main(String[] args) throws Exception {
        Button button = new Button();

        FileOutputStream fos = new FileOutputStream("state.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(button.getCurrentState());
    }
}

interface Car {
    // String NAME = "Tom";
    // public final static String NAME = "Tom";
}