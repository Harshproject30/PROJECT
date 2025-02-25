import java.util.*;

// Base User class
class User {
    protected String name;
    protected String phone;
    
    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Phone: " + phone);
    }
}

// Passenger class
class Passenger extends User {
    public Passenger(String name, String phone) {
        super(name, phone);
    }
}

// Driver class
class Driver extends User {
    private String carModel;
    private String carNumber;
    
    public Driver(String name, String phone, String carModel, String carNumber) {
        super(name, phone);
        this.carModel = carModel;
        this.carNumber = carNumber;
    }
    
    public void displayDriverInfo() {
        displayInfo();
        System.out.println("Car: " + carModel + ", Number: " + carNumber);
    }
}

// Cab class
class Cab {
    private String cabType;
    private double farePerKm;
    private boolean isAvailable;
    private Driver driver;
    
    public Cab(String cabType, double farePerKm, Driver driver) {
        this.cabType = cabType;
        this.farePerKm = farePerKm;
        this.isAvailable = true;
        this.driver = driver;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void bookCab() {
        isAvailable = false;
    }
    
    public void completeRide() {
        isAvailable = true;
    }
    
    public double calculateFare(double distance) {
        return farePerKm * distance;
    }
    
    public Driver getDriver() {
        return driver;
    }
    
    public String getCabType() {
        return cabType;
    }
}

// Booking class
class Booking {
    private Passenger passenger;
    private Cab cab;
    private double distance;
    private double fare;
    
    public Booking(Passenger passenger, Cab cab, double distance) {
        this.passenger = passenger;
        this.cab = cab;
        this.distance = distance;
        this.fare = cab.calculateFare(distance);
    }
    
    public void confirmBooking() {
        if (cab.isAvailable()) {
            cab.bookCab();
            System.out.println("\nCab booked successfully!");
            System.out.println("Driver Details:");
            cab.getDriver().displayDriverInfo();
            System.out.println("Total Fare: Rs." + fare);
        } else {
            System.out.println("No cabs available!");
        }
    }
}

public class CabBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create some drivers
        Driver d1 = new Driver("Raj", "9876543210", "Sedan", "MH12AB1234");
        Driver d2 = new Driver("Amit", "7896541230", "Hatchback", "MH14CD5678");
        
        // Create cabs
        Cab cab1 = new Cab("Sedan", 15.0, d1);
        Cab cab2 = new Cab("Hatchback", 12.0, d2);
        
        List<Cab> availableCabs = new ArrayList<>();
        availableCabs.add(cab1);
        availableCabs.add(cab2);
        
        // User Input for Passenger
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        Passenger passenger = new Passenger(name, phone);
        
        // Choose Cab Type
        System.out.println("\nAvailable Cab Types: 1. Sedan 2. Hatchback");
        System.out.print("Select cab type (1 or 2): ");
        int cabChoice = scanner.nextInt();
        scanner.nextLine();
        
        Cab selectedCab = null;
        for (Cab cab : availableCabs) {
            if ((cabChoice == 1 && cab.getCabType().equals("Sedan")) || (cabChoice == 2 && cab.getCabType().equals("Hatchback"))) {
                if (cab.isAvailable()) {
                    selectedCab = cab;
                    break;
                }
            }
        }
        
        if (selectedCab == null) {
            System.out.println("No available cabs of the selected type!");
        } else {
            // Enter Ride Distance
            System.out.print("Enter ride distance (km): ");
            double distance = scanner.nextDouble();
            
            // Book the ride
            Booking booking = new Booking(passenger, selectedCab, distance);
            booking.confirmBooking();
        }
        scanner.close();
    }
}
