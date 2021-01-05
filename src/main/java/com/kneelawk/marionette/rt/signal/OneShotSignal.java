package com.kneelawk.marionette.rt.signal;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This signal can only be called once. If listeners are added to this signal after it has already been sent, those
 * listeners will be invoked immediately.
 */
public class OneShotSignal implements Signal {
    private final AtomicReference<Listener> listeners = new AtomicReference<>(null);
    private final AtomicBoolean signaled = new AtomicBoolean(false);

    /**
     * Triggers this signal.
     * <p>
     * Once this signal has been triggered, it cannot be re-triggered and subsequent calls will return {@code false}.
     *
     * @return whether this signal was successfully triggered.
     */
    @Override
    public boolean signal() {
        if (!signaled.getAndSet(true)) {
            Listener head = listeners.getAndSet(Listener.TOMBSTONE);

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
        return false;
    }

    /**
     * Adds a listener to this signal.
     * <p>
     * If this signal is in an un-signalled state, then all listeners will be invoked when the signal is triggered. If
     * the signal has already been triggered, then all listeners added will be invoked immediately.
     *
     * @param runnable the runnable to run when this signal is triggered.
     * @param executor the executor to run the runnable on.
     */
    @Override
    public void addListener(Runnable runnable, Executor executor) {
        // A lot of this code is practically copied from Guava's AbstractFuture
        checkNotNull(runnable, "Runnable was null");
        checkNotNull(executor, "Executor was null");

        Listener oldHead = listeners.get();
        if (oldHead != Listener.TOMBSTONE) {
            Listener newHead = new Listener(runnable, executor);
            do {
                newHead.next = oldHead;
                if (listeners.compareAndSet(oldHead, newHead)) {
                    return;
                }
                oldHead = listeners.get();
            } while (oldHead != Listener.TOMBSTONE);
        }

        // Maybe handle RuntimeExceptions eventually.
        executor.execute(runnable);
    }
}
