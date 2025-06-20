package com.jayanth.rideshare.observer;

import com.jayanth.rideshare.model.Driver;

public class ConsoleNotificationService implements DriverObserver {
    @Override
    public void notifyDriverAssigned(Driver driver, String message) {
        System.out.println("[NOTIFY] Driver " + driver.getName() + ": " + message);
    }
}
