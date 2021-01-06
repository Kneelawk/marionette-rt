package com.kneelawk.marionette.rt.instance;

import com.google.common.util.concurrent.SettableFuture;

import java.rmi.RemoteException;

public abstract class AbstractMinecraftInstance {
    protected final Process process;
    protected final LogWatcherThread outWatcher;
    protected final LogWatcherThread errWatcher;

    public AbstractMinecraftInstance(Process process, LogWatcherThread outWatcher,
                                     LogWatcherThread errWatcher) {
        this.process = process;
        this.outWatcher = outWatcher;
        this.errWatcher = errWatcher;
    }

    public Process getProcess() {
        return process;
    }

    public abstract void startMinecraft() throws RemoteException;

    protected void chainFutureError(SettableFuture<?> future) {
        outWatcher.chainFuture(future);
        errWatcher.chainFuture(future);
    }

    public void finish() throws InterruptedException, InstanceException {
        if (process.waitFor() != 0) {
            throw new InstanceException("Minecraft exited with non-zero exit code.");
        }

        outWatcher.checkError();
        errWatcher.checkError();
    }
}
