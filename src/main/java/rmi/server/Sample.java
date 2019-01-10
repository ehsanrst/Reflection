package rmi.server;

import rmi.implement.SampleImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//2.Developing the Implementation Class (Remote Object)
public class Sample extends UnicastRemoteObject implements SampleImpl {

    public String pm;

    public Sample() throws RemoteException {
        super();
    }

    @Override
    public String print(String str) throws RemoteException {
        setPm(str.concat(" is registered!"));
        return getPm();
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
