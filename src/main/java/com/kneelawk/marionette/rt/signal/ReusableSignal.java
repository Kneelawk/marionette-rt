package com.kneelawk.marionette.rt.signal;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This signal can be called multiple times. When called, every current listener will be invoked and the list of
 * listeners will be cleared.
 */
public class ReusableSignal implements Signal {
    private final AtomicReference<Listener> listeners = new AtomicReference<>(null);

    /**
     * Triggers this signal.
     *
     * @return whether this signal was successfully triggered. This will always be true.
     */
    @Override
    public boolean signal() {
        Listener head = listeners.getAndSet(null);

        Listener reversedHead = null;
        while (head != null) {
            Listener tmp = head;
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
     * When this signal is triggered, all registered listeners are invoked and removed. If a listener should be
     * re-invoked, it must be re-added.
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
