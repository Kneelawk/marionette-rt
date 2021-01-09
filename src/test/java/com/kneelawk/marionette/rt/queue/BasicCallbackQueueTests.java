package com.kneelawk.marionette.rt.queue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicCallbackQueueTests {
    @Test
    void testQueue() {
        BasicCallbackQueue<Runnable> queue = new BasicCallbackQueue<>();
        AtomicBoolean called = new AtomicBoolean(false);
        queue.push(() -> called.set(true));
        assertFalse(called.get(), "Pre-first-poll callback state. Listener should not be called yet.");
        queue.pollAll((callback, currentThread) -> callback.run());
        assertTrue(called.get(), "Post-first-poll callback state. Listener should be called.");
        called.set(false);
        queue.pollAll((callback, currentThread) -> callback.run());
        assertFalse(called.get(), "Post-second-poll callback state. Listener should not be re-called.");
    }

    @Test
    void testReusedQueue() {
        BasicCallbackQueue<Runnable> queue = new BasicCallbackQueue<>();
        AtomicBoolean called = new AtomicBoolean(false);
        queue.push(() -> called.set(true));
        assertFalse(called.get(), "Pre-first-poll callback state. Listener should not be called yet.");
        queue.pollAll((callback, currentThread) -> callback.run());
        assertTrue(called.get(), "Post-first-poll callback state. Listener should be called.");
        called.set(false);
        queue.push(() -> called.set(true));
        assertFalse(called.get(), "Pre-second-poll callback state. Listener should not be re-called yet.");
        queue.pollAll((callback, currentThread) -> callback.run());
        assertTrue(called.get(), "Post-second-poll callback state. Listener should be re-called.");
    }
}
