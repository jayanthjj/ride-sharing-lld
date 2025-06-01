package com.jayanth.rideshare.observer;

import java.util.ArrayList;
import java.util.List;
import com.jayanth.rideshare.model.Driver;

public class NotificationService {
    private final List<DriverObserver> observers = new ArrayList<>();

    public void registerObserver(DriverObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(Driver driver, String message) {
        for (DriverObserver obs : observers) {
            obs.notifyDriverAssigned(driver, message);
        }
    }
}
