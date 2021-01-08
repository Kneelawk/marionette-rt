package com.kneelawk.marionette.rt.queue;

import com.kneelawk.marionette.rt.util.BlockingCurrentThreadExecutor;
import com.kneelawk.marionette.rt.util.MarionetteExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This basic implementation of the callback queue.
 *
 * @param <C> the type of callback this queue handles.
 */
public class BasicCallbackQueue<C> implements CallbackQueue<C> {
    private final AtomicReference<Listener<C>> listeners = new AtomicReference<>(null);

    @Override
    public void push(C callback) {
        checkNotNull(callback, "Callback was null.");

        Listener<C> newHead = new Listener<>(callback);
        do {
            newHead.next = listeners.get();
        } while (!listeners.compareAndSet(newHead.next, newHead));
    }

    @Override
    public void pollAll(CallbackConsumer<C> consumer) {
        Listener<C> head = listeners.getAndSet(null);

        Listener<C> reversedHead = null;
        while (head != null) {
            Listener<C> tmp = head;
            head = head.next;
            tmp.next = reversedHead;
            reversedHead = tmp;
        }

        Exception error = null;

        while (reversedHead != null) {
            C currentCallback = reversedHead.callback;
            BlockingCurrentThreadExecutor executor = new BlockingCurrentThreadExecutor();
            Future<Void> callbackFuture = MarionetteExecutors.getCallbackConsumerExecutor().submit(() -> {
                try {
                    consumer.accept(currentCallback, executor);
                } finally {
                    executor.shutdown();
                }

                return null;
            });

            executor.run();

            try {
                callbackFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                if (error == null) {
                    error = e;
                } else {
                    error.addSuppressed(e);
                }
            }

            reversedHead = reversedHead.next;
        }

        if (error != null) {
            throw new RuntimeException("Exception while polling queue: " + error.getMessage(), error);
        }
    }
}
