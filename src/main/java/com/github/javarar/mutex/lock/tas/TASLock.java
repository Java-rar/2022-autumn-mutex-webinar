package com.github.javarar.mutex.lock.tas;

import com.github.javarar.mutex.lock.Lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

// N-потоков
public class TASLock implements Lock {

    private final AtomicBoolean state = new AtomicBoolean(false);

    @Override
    public void lock() {
        while (state.getAndSet(true)) {} // spin
    }

    @Override
    public void unlock() {
        state.set(false);
    }

}
