package com.jayanth.rideshare.controller;

import com.jayanth.rideshare.model.*;
import com.jayanth.rideshare.service.*;
import com.jayanth.rideshare.strategy.*;
import com.jayanth.rideshare.observer.*;

public class RideController {
    private final RideService rideService;
    private final DriverService driverService;

    public RideController() {
        DriverService driverService = new DriverService();
        NotificationService notificationService = new NotificationService();
        notificationService.registerObserver(new ConsoleNotificationService());

        this.driverService = driverService;
        this.rideService = new RideService(driverService, notificationService);
    }

    public void registerDriver(String name, Location location) {
        driverService.registerDriver(new Driver(name, location));
    }

    public Ride bookRide(Rider rider, Location dropLocation) {
        return rideService.createRide(rider, dropLocation);
    }

    public void completeRide(String rideId) {
        rideService.completeRide(rideId, new SimpleFareStrategy());
    }

    public void printRideHistory() {
        rideService.getRideHistory().values().forEach(System.out::println);
    }
}
