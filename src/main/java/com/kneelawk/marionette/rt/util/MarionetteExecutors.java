package com.kneelawk.marionette.rt.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class holding general use executors.
 */
public class MarionetteExecutors {
    private static final AtomicInteger CALLBACK_CONSUMER_EXECUTOR_NUMBER = new AtomicInteger(1);
    private static final ExecutorService CALLBACK_CONSUMER_EXECUTOR = Executors.newCachedThreadPool(runnable -> {
        Thread thread = new Thread(runnable);
        thread.setName("Marionette-Callback-Thread-" + CALLBACK_CONSUMER_EXECUTOR_NUMBER.getAndIncrement());
        // Making these threads daemons is probably ok for now. If this becomes an issue, we can just refactor the
        // ThreadWatchUnbinder to shut down this executor when it notices all the other threads are gone.
        thread.setDaemon(true);
        return thread;
    });

    /**
     * Gets the general use executor for running callback consumers.
     *
     * @return the executor for running callback consumers.
     */
    public static ExecutorService getCallbackConsumerExecutor() {
        return CALLBACK_CONSUMER_EXECUTOR;
    }
}
