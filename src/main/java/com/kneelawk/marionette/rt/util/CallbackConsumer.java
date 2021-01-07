package com.kneelawk.marionette.rt.util;

import com.kneelawk.marionette.rt.rmi.CurrentThread;

/**
 * Represents an operation that consumes a callback along with an RMI-enabled executor to enable the callback to execute
 * more code in this process.
 * <p>
 * Callback consumers are normally used when polling a callback queue.
 *
 * @param <C> the type of callback to be consumed.
 */
@FunctionalInterface
public interface CallbackConsumer<C> {
    /**
     * Consumes the given callback. This is expected to be where the callback is called.
     *
     * @param callback      the callback to execute.
     * @param currentThread the RMI-enabled executor to enable to callback to execute more code in this process.
     * @throws Exception if an exception occurs while consuming this callback.
     */
    void accept(C callback, CurrentThread currentThread) throws Exception;
}
