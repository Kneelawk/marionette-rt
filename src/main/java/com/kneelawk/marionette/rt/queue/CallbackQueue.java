package com.kneelawk.marionette.rt.queue;

/**
 * Interface for callback queues.
 * <p>
 * Note that callback queues are designed to be used to send callbacks between threads and are therefore thread-safe.
 *
 * @param <C> the type of callback this queue manages.
 */
public interface CallbackQueue<C> {
    /**
     * Adds a callback to this queue.
     *
     * @param callback the callback to add.
     */
    void push(C callback);

    /**
     * Consumes all callbacks currently in this queue.
     *
     * @param consumer the function that consumes callbacks.
     */
    void pollAll(CallbackConsumer<C> consumer);
}
