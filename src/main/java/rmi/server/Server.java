package rmi.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//3.Developing the Server Program
public class Server {
    public static void main(String[] args)
            throws RemoteException,
            MalformedURLException,
            AlreadyBoundException {

        Sample obj = new Sample(); //Remote Object
        /* To record 'Remote Object' into 'RMI Registry' */
        Registry reg = LocateRegistry.createRegistry(1099);

        Naming.bind("//localhost/Sample", obj); //Binding the Remote Object(obj) with a name(Sample)

        System.out.println("Clients can invoke the methods....");
    }
}

/*
 * "RMI Registry" can define as a book that record every needed Object for Client via a given name
 * by Server and Client can access to Objects just by calling its name.
 * */

/**
 * CAUTION:
 * Before starting the compute engine, you need to start the RMI registry. The RMI registry is a simple
 * server-side bootstrap naming facility that enables remote clients to obtain a reference to an initial
 * remote object.
 * <p>
 * The static UnicastRemoteObject.exportObject method exports the supplied remote object so that it can
 * receive invocations of its remote methods from remote clients.
 */