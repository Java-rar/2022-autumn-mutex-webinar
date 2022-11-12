package com.github.javarar.mutex.showtime;

import org.openjdk.jmh.infra.Blackhole;

// link: https://youtu.be/q2wtSR3kD_I
public class InnerIntrinsicIntRegister {

    // удобно синхронизироваться если есть вложенные классы
    private final Object LOCK = new Object();

    private int value;

    public int get() {
        // some extra not concurrent logic
        Blackhole.consumeCPU(8);

        synchronized (LOCK) {
            return value = value + 1;
        }
    }
}
