package com.kneelawk.marionette.rt.signal;

import java.util.concurrent.Executor;

/**
 * Interface which all signals will implement.
 * <p>
 * A signal is a data-less synchronization system. Listeners can be added to a signal and the signal can be signalled.
 */
public interface Signal {
    /**
     * Triggers this signal.
     *
     * @return whether the signal was successfully triggered. An unsuccessful trigger could be caused by a one-shot
     * signal already being signaled.
     */
    boolean signal();

    /**
     * Adds a listener to this signal.
     *
     * @param runnable the runnable to run when this signal is triggered.
     * @param executor the executor to run the runnable on.
     */
    void addListener(Runnable runnable, Executor executor);
}
