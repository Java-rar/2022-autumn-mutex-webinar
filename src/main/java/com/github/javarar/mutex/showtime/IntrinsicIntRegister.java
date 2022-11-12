package com.github.javarar.mutex.showtime;

public class IntrinsicIntRegister {

    private int value;

    public synchronized int get() {
        return value = value + 1;
    }
}
