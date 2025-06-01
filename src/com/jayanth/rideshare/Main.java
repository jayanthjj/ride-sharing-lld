package com.jayanth.rideshare;

import com.jayanth.rideshare.controller.RideController;
import com.jayanth.rideshare.model.*;

public class Main {
    public static void main(String[] args) {
        RideController controller = new RideController();

        controller.registerDriver("Arjun", new Location(12.9611, 77.6387));
        controller.registerDriver("Kiran", new Location(12.9611, 77.6388));

        Rider rider = new Rider("Ravi", new Location(12.9716, 77.5946));
        Location drop = new Location(12.9250, 77.5938);

        Ride ride = controller.bookRide(rider, drop);
        if (ride != null) {
            controller.completeRide(ride.getId());
        }

        controller.printRideHistory();
    }
}
