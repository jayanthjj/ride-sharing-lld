# Ride Sharing System

A Java-based low-level design (LLD) implementation of a ride sharing application using object-oriented design principles and design patterns.

## Overview

This project demonstrates a simplified ride-sharing application with core functionalities including:
- Driver registration and management
- Ride booking and completion
- Driver-rider matchmaking based on proximity
- Fare calculation using strategies
- Driver notification using observer pattern

## System Architecture

The system follows an MVC architecture with the following components:

### 1. Model Layer
Contains domain entities representing the core business objects:
- `Ride`: Represents a ride with rider, driver, locations, and status
- `Driver`: Driver information with availability status
- `Rider`: User requesting a ride
- `Location`: Geographical coordinates with distance calculation
- `RideStatus`: Enum for tracking ride states (REQUESTED, IN_PROGRESS, COMPLETED)

### 2. Service Layer
Contains business logic and orchestrates interactions between models:
- `RideService`: Manages ride creation, completion, and maintains ride history
- `DriverService`: Handles driver registration and finding available drivers

### 3. Controller Layer
Entry point for client requests that delegates to appropriate services:
- `RideController`: Coordinates between services and exposes APIs for clients

### 4. Design Patterns Implemented

#### Strategy Pattern
Used for implementing different fare calculation strategies:
- `FareStrategy`: Interface defining fare calculation contract
- `SimpleFareStrategy`: Implementation with base fare + per km rate

#### Observer Pattern
Used for driver notifications:
- `NotificationService`: Subject managing observers and triggering notifications
- `DriverObserver`: Interface for notification receivers
- `ConsoleNotificationService`: Implementation that prints notifications to console

## Key Features

### Driver Management
- Driver registration with location tracking
- Availability status management

### Ride Booking
- Request rides with current and destination locations
- Automatic matching with nearest available driver
- Distance-based fare calculation

### Notification System
- Drivers receive notifications about assigned rides

## Usage Example

The main class demonstrates a simple usage flow:

```java
// Create a controller
RideController controller = new RideController();

// Register drivers with their locations
controller.registerDriver("Arjun", new Location(12.9611, 77.6387));
controller.registerDriver("Kiran", new Location(12.9611, 77.6388));

// Create a rider and specify drop location
Rider rider = new Rider("Ravi", new Location(12.9716, 77.5946));
Location drop = new Location(12.9250, 77.5938);

// Book and complete a ride
Ride ride = controller.bookRide(rider, drop);
if (ride != null) {
    controller.completeRide(ride.getId());
}

// View ride history
controller.printRideHistory();
```

## Design Principles Implemented

1. **Single Responsibility Principle**: Each class has a well-defined responsibility
   - `RideService` focuses on ride management
   - `DriverService` handles driver-related operations
   - `NotificationService` manages notifications

2. **Open/Closed Principle**: System is open for extension but closed for modification
   - New fare strategies can be added without changing existing code
   - New notification methods can be implemented by creating new observer classes

3. **Dependency Inversion**: High-level modules depend on abstractions
   - Services depend on interfaces rather than concrete implementations
   - Fare calculation depends on `FareStrategy` interface

4. **Interface Segregation**: Clients only depend on methods they use
   - `DriverObserver` has a focused API for notifications

5. **Encapsulation**: Internal state is hidden and accessed through well-defined interfaces
   - Models expose getters but control how properties are updated

## Technical Details

### Location Distance Calculation
```java
public double distanceTo(Location other) {
    double dx = this.latitude - other.latitude;
    double dy = this.longitude - other.longitude;
    return Math.sqrt(dx*dx + dy*dy);
}
```

### Fare Calculation
```java
public double calculateFare(Location start, Location end) {
    double distance = start.distanceTo(end);
    return BASE_FARE + (distance * PER_KM_RATE);
}
```
Base fare: ₹50.0
Per kilometer rate: ₹10.0

## Future Enhancements

1. **Additional Features**
   - User authentication and profile management
   - Different vehicle types and categorization
   - Multiple fare strategies (peak hours, premium rides)
   - Rating system for drivers and riders
   - Payment integration

2. **Technical Improvements**
   - Persistence layer with database integration
   - Concurrency handling for multi-threaded access
   - API endpoints for external clients
   - Enhanced validation and error handling
   - Unit and integration tests

## Project Structure
```
src/
├── com/
│   └── jayanth/
│       └── rideshare/
│           ├── Main.java
│           ├── controller/
│           │   └── RideController.java
│           ├── model/
│           │   ├── Driver.java
│           │   ├── Location.java
│           │   ├── Ride.java
│           │   ├── Rider.java
│           │   └── RideStatus.java
│           ├── observer/
│           │   ├── ConsoleNotificationService.java
│           │   ├── DriverObserver.java
│           │   └── NotificationService.java
│           ├── service/
│           │   ├── DriverService.java
│           │   └── RideService.java
│           └── strategy/
│               ├── FareStrategy.java
│               └── SimpleFareStrategy.java
```

## Running the Application

1. Clone the repository
2. Open in your favorite Java IDE (IntelliJ IDEA, Eclipse, etc.)
3. Run the `Main.java` file

## Requirements

- Java 8 or higher
- No external dependencies