package rmi.implement;

import java.rmi.Remote;
import java.rmi.RemoteException;

//1.Define Remote Interface
public interface SampleImpl extends Remote {
    String print(String str) throws RemoteException;
}
