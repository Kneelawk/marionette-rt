package com.kneelawk.marionette.rt.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIRunnable extends Remote {
    void run() throws RemoteException;
}
