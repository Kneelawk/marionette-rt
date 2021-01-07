package com.kneelawk.marionette.rt.util;

import com.kneelawk.marionette.rt.rmi.CurrentThread;
import com.kneelawk.marionette.rt.rmi.RMIRunnable;
import com.kneelawk.marionette.rt.rmi.RMIUtils;

import java.rmi.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * Utility class for marionette-rt relating to executors and executions, especially involving RMI.
 */
public class ExecutionUtils {

    /**
     * Runs a Runnable in the given RMI-enabled executor if it is of this process.
     * <p>
     * This function is normally called from an RMI thread to execute code in a specified non-RMI thread.
     *
     * @param thread   the RMI-enabled executor to run the runnable on.
     * @param runnable the runnable to run in the executor.
     * @throws IllegalArgumentException if the RMI-enabled executor is from a different process.
     */
    public static void executeIn(CurrentThread thread, Runnable runnable) {
        CurrentThread original = RMIUtils.findOriginal(thread);

        if (!(original instanceof ExecutorService)) {
            throw new IllegalArgumentException("Attempted to run something on an expired or different-process thread.");
        }

        try {
            ((ExecutorService) original).submit(runnable).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO: Build an actual exception hierarchy for this.
            throw new RuntimeException("Exception while executing in existing thread: " + e.getMessage(), e);
        }
    }

    /**
     * Runs a callable in the given RMI-enabled executor if it is of this process.
     * <p>
     * This function is normally called from an RMI thread to execute code in a specified non-RMI thread.
     *
     * @param thread   the RMI-enabled executor to run the callable on.
     * @param callable the callable to run in the executor.
     * @param <T>      the return type of the callable.
     * @return any value that the callable returns.
     */
    public static <T> T executeIn(CurrentThread thread, Callable<T> callable) {
        CurrentThread original = RMIUtils.findOriginal(thread);

        if (!(original instanceof ExecutorService)) {
            throw new IllegalArgumentException("Attempted to run something on an expired or different-process thread.");
        }

        try {
            return ((ExecutorService) original).submit(callable).get();
        } catch (InterruptedException e) {
            // TODO: Figure out what to do with this exception.
            throw new RuntimeException("Execution in thread interrupted", e);
        } catch (ExecutionException e) {
            // TODO: Build an actual exception hierarchy for this.
            throw new RuntimeException("Exception while executing in existing thread: " + e.getMessage(), e);
        }
    }

    /**
     * Wraps a RMI-enabled runnable in a regular Runnable for general use.
     *
     * @param runnable the RMI-enabled runnable to wrap.
     * @return the wrapped runnable.
     */
    public static Runnable toRunnable(RMIRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (RemoteException e) {
                // TODO: Handle this RemoteException properly.
                e.printStackTrace();
            }
        };
    }
}
