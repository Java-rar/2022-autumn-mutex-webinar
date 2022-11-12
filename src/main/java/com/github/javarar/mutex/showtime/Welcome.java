package com.github.javarar.mutex.showtime;

import com.github.javarar.mutex.lock.Lock;

@SuppressWarnings("all")
public class Welcome {

    public static void main(String[] args) {

        final Lock mutex = null; // lock implementation

        Runnable command = () -> {

            mutex.lock();

            try {
                // critical section
            } finally {
                mutex.unlock();
            }
        };
    }

}
