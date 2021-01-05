package com.kneelawk.marionette.rt.signal;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This signal can be called multiple times. Every time this signal is called, all existing listeners will be called.
 */
public class RepeatedSignal implements Signal {
    private final AtomicReference<Listener> listeners = new AtomicReference<>(null);

    /**
     * Triggers this signal.
     *
     * @return whether this signal was successfully triggered. This will always be true.
     */
    @Override
    public boolean signal() {
        Listener head = listeners.get();

        Listener reversedHead = null;
        while (head != null) {
            Listener tmp = new Listener(head.runnable, head.executor);
            head = head.next;
            tmp.next = reversedHead;
            reversedHead = tmp;
        }

        while (reversedHead != null) {
            // Maybe handle RuntimeExceptions eventually.
            reversedHead.executor.execute(reversedHead.runnable);
            reversedHead = reversedHead.next;
        }

        return true;
    }

    /**
     * Adds a listener to this signal.
     * <p>
     * When this signal is triggered, all registered listeners are invoked. If this signal is triggered multiple times,
     * then the registered listeners will be invoked multiple times.
     *
     * @param runnable the runnable to run when this signal is triggered.
     * @param executor the executor to run the runnable on.
     */
    @Override
    public void addListener(Runnable runnable, Executor executor) {
        checkNotNull(runnable, "Runnable was null");
        checkNotNull(executor, "Executor was null");

        Listener newHead = new Listener(runnable, executor);
        do {
            newHead.next = listeners.get();
        } while (!listeners.compareAndSet(newHead.next, newHead));
    }
}
