package com.kneelawk.marionette.rt.util;

import com.google.common.util.concurrent.ForwardingExecutorService;
import com.kneelawk.marionette.rt.rmi.CurrentThread;
import com.kneelawk.marionette.rt.rmi.RMIRunnable;
import com.kneelawk.marionette.rt.rmi.RMIUtils;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class ExecutionUtilsTests {
    @Test
    void testToRunnable() {
        AtomicBoolean run = new AtomicBoolean(false);
        RMIRunnable runnable = () -> run.set(true);
        Runnable wrapped = ExecutionUtils.toRunnable(runnable);
        assertFalse(run.get(), "Wrapping a RMIRunnable should not call it.");
        wrapped.run();
        assertTrue(run.get(), "Calling the wrapper runnable should call the underlying RMIRunnable.");
    }

    @Test
    void testExecuteInThrows() {
        assertThrows(IllegalArgumentException.class, () -> ExecutionUtils.executeIn(new CurrentThreadDummy(), () -> {
        }), "Execute in (Runnable) should throw an exception when run in a fake current thread.");
        assertThrows(IllegalArgumentException.class,
                () -> ExecutionUtils.executeIn(new CurrentThreadDummy(), () -> null),
                "Execute in (Callable) should throw an exception when run in a fake current thread.");
    }

    @Test
    void testExecuteInRunnable() throws RemoteException, InterruptedException {
        AtomicReference<String> threadName = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);
        CurrentThread thread = new CurrentThreadImpl();
        CurrentThread exported = RMIUtils.export(thread);
        ExecutionUtils.executeIn(exported, () -> {
            threadName.set(Thread.currentThread().getName());
            latch.countDown();
        });
        latch.await();
        assertEquals("CurrentThread", threadName.get());
    }

    @Test
    void testExecuteInCallable() throws RemoteException {
        CurrentThread thread = new CurrentThreadImpl();
        CurrentThread exported = RMIUtils.export(thread);
        String threadName = ExecutionUtils.executeIn(exported, () -> Thread.currentThread().getName());
        assertEquals("CurrentThread", threadName);
    }

    private static class CurrentThreadImpl extends ForwardingExecutorService implements CurrentThread {
        final ExecutorService executor = Executors.newSingleThreadExecutor(runnable -> {
            Thread t = new Thread(runnable);
            t.setName("CurrentThread");
            t.setDaemon(true);
            return t;
        });

        @Override
        protected ExecutorService delegate() {
            return executor;
        }

        @Override
        public boolean isExpired() throws RemoteException {
            return isShutdown();
        }
    }

    private static class CurrentThreadDummy implements CurrentThread {
        @Override
        public boolean isExpired() throws RemoteException {
            return false;
        }
    }
}
