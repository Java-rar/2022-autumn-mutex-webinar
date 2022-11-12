package com.github.javarar.mutex.lock.tas;

import com.github.javarar.mutex.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock {

    private final AtomicBoolean state = new AtomicBoolean();

    @Override
    public void lock() {
        while (true) {
            while (state.get()) {} // spin
            if(!state.getAndSet(true)) return;
        }
    }

    @Override
    public void unlock() {
        state.set(false);
    }
}
