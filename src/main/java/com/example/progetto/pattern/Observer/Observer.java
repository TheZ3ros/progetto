package com.example.progetto.pattern.Observer;

import com.almasb.fxgl.notification.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

    void update(Notification notification) throws RemoteException;
}
