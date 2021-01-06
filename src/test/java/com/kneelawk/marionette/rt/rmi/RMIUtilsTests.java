package com.kneelawk.marionette.rt.rmi;

import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

public class RMIUtilsTests {
    @Test
    void testFindOriginal() throws RemoteException {
        assertNull(RMIUtils.findOriginal(new Object()),
                "Finding the original version of an object that was not exported should return null.");
        RMIRunnable runnable = () -> System.out.println("Hello");
        RMIRunnable exported = RMIUtils.export(runnable);
        assertNotNull(exported, "An exported object should never be null.");
        assertNotEquals(runnable, exported, "An exported object should not equal its non-exported counterpart.");
        assertEquals(runnable, RMIUtils.findOriginal(exported),
                "Finding the original version of an object that was exported should return the original object.");
    }

    @Test
    void testRequireOriginal() throws RemoteException {
        assertThrows(IllegalArgumentException.class, () -> RMIUtils.requireOriginal(new Object()),
                "Requiring the original version of an object that was not exported should throw.");
        RMIRunnable runnable = () -> System.out.println("Hello");
        RMIRunnable exported = RMIUtils.export(runnable);
        assertNotNull(exported, "An exported object should never be null.");
        assertNotEquals(runnable, exported, "An exported object should not equal its non-exported counterpart.");
        assertEquals(runnable, RMIUtils.requireOriginal(exported),
                "Requiring the original version of an object that was exported should return the original object.");
    }
}
