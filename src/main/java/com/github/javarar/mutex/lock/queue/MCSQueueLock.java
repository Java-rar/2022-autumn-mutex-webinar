package com.github.javarar.mutex.lock.queue;

import com.github.javarar.mutex.lock.Lock;

import java.util.concurrent.atomic.AtomicReference;

public class MCSQueueLock implements Lock {

    private final AtomicReference<LockNode> tail
            = new AtomicReference<>(null);

    private final ThreadLocal<LockNode> threadNode
            = ThreadLocal.withInitial(LockNode::new);

    @Override
    public void lock() {
        LockNode thread = threadNode.get();
        LockNode predecessor = tail.getAndSet(thread);

        if(predecessor != null) {
            thread.locked = true;
            // Поток X записывает себя приемником в Поток X-1
            predecessor.next = thread;

            // ждем пока лок отпустит наш предшественник
            while (thread.locked) {}
        }
    }

    @Override
    public void unlock() {
        LockNode thread = threadNode.get();

        // если у нас нет приемника, то
        if(thread.next == null) {
            // пробуем занулить хвость (фактически откатываем лок в изначальное, дефолтное состояние) - тогда можем выйти
            if(tail.compareAndSet(thread, null)) return;

            // иначе, кто-то уже прошел строчку 18 и скоро будет готов стать приемником в строчку 23 - ждем этого моммента
            while (thread.next == null) {}
        }

        // отпускаем лок - пропускаем поток в строчке 26
        thread.next.locked = false;
        // очищаем поле локальной ноды, чтобы можно было ее переиспользовать при повторной попытке блокировки
        thread.next = null;
    }

    private static class LockNode {
        private volatile boolean locked = false;
        private volatile LockNode next = null;
    }
}
