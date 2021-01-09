package com.kneelawk.marionette.rt.rmi;

import com.kneelawk.marionette.rt.util.ThreadWatchUnbinder;

import java.lang.ref.WeakReference;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.WeakHashMap;

public class RMIUtils {
    private static final Map<Object, WeakReference<Object>> EXPORTS = new WeakHashMap<>();
    private static final ThreadWatchUnbinder UNBINDER =
            new ThreadWatchUnbinder(RMIUtils::unbindAll, "RMIUtils-Unbinder");

    static {
        UNBINDER.start();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Remote> T export(T original) throws RemoteException {
        T export = (T) UnicastRemoteObject.exportObject(original, 0);
        EXPORTS.put(export, new WeakReference<>(original));
        return export;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findOriginal(T export) {
        WeakReference<Object> ref = EXPORTS.get(export);

        if (ref == null) {
            return null;
        }

        return (T) ref.get();
    }

    public static <T> T requireOriginal(T export) {
        T original = findOriginal(export);

        if (original == null) {
            throw new IllegalArgumentException(
                    "Unable to find original RMI exported object. Maybe the object was exported in another process?");
        }

        return original;
    }

    private static void unbindAll() {
        System.out.println("Unbinding all RMI objects...");
        for (WeakReference<Object> originalRef : EXPORTS.values()) {
            Object original = originalRef.get();
            if (original != null) {
                try {
                    UnicastRemoteObject.unexportObject((Remote) original, true);
                } catch (NoSuchObjectException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
