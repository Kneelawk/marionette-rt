package com.kneelawk.marionette.rt.signal;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OneShotSignalTests {
    @Test
    void testSignal() {
        OneShotSignal signal = new OneShotSignal();
        AtomicBoolean activated = new AtomicBoolean(false);
        signal.addListener(() -> activated.set(true), MoreExecutors.directExecutor());
        assertFalse(activated.get(), "Pre-signal listener state. Listener should not be signalled yet.");
        signal.signal();
        assertTrue(activated.get(), "Post-signal listener state. Listener should be signalled.");
    }

    @Test
    void testPostSignal() {
        OneShotSignal signal = new OneShotSignal();
        signal.signal();
        AtomicBoolean activated = new AtomicBoolean(false);
        signal.addListener(() -> activated.set(true), MoreExecutors.directExecutor());
        assertTrue(activated.get(), "Post-signal listener state. Listener should be automatically signalled.");
    }
}
