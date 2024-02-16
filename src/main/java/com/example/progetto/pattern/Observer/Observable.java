package com.example.progetto.pattern.Observer;

import com.almasb.fxgl.notification.Notification;

import java.rmi.RemoteException;
import java.util.Observer;

public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notify(Notification notification) throws RemoteException;
}
