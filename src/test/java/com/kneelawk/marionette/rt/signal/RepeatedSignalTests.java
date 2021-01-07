package com.kneelawk.marionette.rt.signal;

import com.google.common.util.concurrent.MoreExecutors;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepeatedSignalTests {
    @Test
    void testSignal() {
        RepeatedSignal signal = new RepeatedSignal();
        AtomicBoolean activated = new AtomicBoolean(false);
        signal.addListener(() -> activated.set(true), MoreExecutors.directExecutor());
        assertFalse(activated.get(), "Pre-first-signal listener state. Listener should not be signalled.");
        signal.signal();
        assertTrue(activated.get(), "Post-first-signal listener state. Listener should be signalled.");
        activated.set(false);
        assertFalse(activated.get(), "Pre-second-signal listener state. Listener should not be re-signalled yet.");
        signal.signal();
        assertTrue(activated.get(), "Post-second-signal listener state. Listener should be re-signalled.");
    }
}
