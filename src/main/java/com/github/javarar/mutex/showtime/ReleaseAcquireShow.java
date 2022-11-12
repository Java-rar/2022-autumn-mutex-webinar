package com.github.javarar.mutex.showtime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReleaseAcquireShow {

    public volatile boolean suspend = true;

    private int v;
    private int x;

    public void release(int value) {
        while (suspend) { Thread.onSpinWait(); }

        this.x = 10;
        this.x = x + 1;
        this.x = x + 1;
        v = value;
    }

    public void acquire() {
        while (suspend) { Thread.onSpinWait(); }

        int vValue = v;
        int xValue = x;
        log.info("v = {}, x = {}", vValue, xValue);
    }

    public static void main(String[] args) {

        Runnable command = () -> {
            final ReleaseAcquireShow show = new ReleaseAcquireShow();

            Runnable release = () -> show.release(1);
            Runnable acquire = show::acquire;

            new Thread(release).start();
            new Thread(acquire).start();

            show.suspend = false;
        };

        for (int i = 0; i < 1000; i++) {
            new Thread(command).start();
        }
    }
}
