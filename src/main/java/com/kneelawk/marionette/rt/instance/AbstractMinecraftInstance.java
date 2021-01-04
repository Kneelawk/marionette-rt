package com.kneelawk.marionette.rt.instance;

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

    public void finish() throws InterruptedException, InstanceException {
        if (process.waitFor() != 0) {
            throw new InstanceException("Minecraft exited with non-zero exit code.");
        }

        outWatcher.checkError();
        errWatcher.checkError();
    }
}
