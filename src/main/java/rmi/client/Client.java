package rmi.client;

import rmi.implement.SampleImpl;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//4.Developing the Client Program
public class Client {
    public static void main(String[] args)
            throws RemoteException,
            MalformedURLException,
            NotBoundException {

        /* Searching for the 'Remote Object' in the 'RMI registry' */
        Registry registry = LocateRegistry.getRegistry(1099);
        SampleImpl stub = (SampleImpl) Naming.lookup("//localhost/Sample");

        String txt = JOptionPane.showInputDialog("What is your name?");
        String message = stub.print(txt);// Invoking the 'Remote Method'
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message);
    }
}

/*
 *  Here seems that Client call a Method from Server but the truth is that the Client calls a Method
 *  that is exist inside of "Stub Object" and this object calls the Method inside Server
 *
 *  "Stub" can define as a cache of Remote Objects inside of Client
 */