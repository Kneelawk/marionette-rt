package com.kneelawk.marionette.rt.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrentThread extends Remote {
    boolean isExpired() throws RemoteException;
}
