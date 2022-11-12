package com.github.javarar.mutex.lock;

import java.util.concurrent.TimeUnit;

public interface SmartLock {

    boolean tryLock(long time, TimeUnit unit);

    void unlock();
}
