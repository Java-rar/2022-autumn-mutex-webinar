package com.github.javarar.mutex.showtime;

public class Register<T> {

    private T value;

    public synchronized void set(T v) {
        if(value == null) {
            this.value = v;
        }
    }

    // очень горячий метод, надо оптимизировать
    public synchronized T get() {
        return value;
    }

}
