package entities;

import java.util.ArrayList;

public class Condo extends Property {
    
    //attributes
    private int numberOfRooms;
    private int floorLevel;
    
    //constructors
    public Condo () { super(); }
    public Condo(String propertyID, int numberOfRooms, int floorLevel, int maxOccupancy, int sqFootage, String streetAddress, String zipCode, String city, String state, String description, String bedType, String amenities, double pricePerDay) {
        super(propertyID, maxOccupancy, sqFootage, streetAddress, zipCode, city, state, description, bedType, amenities, pricePerDay);
        this.numberOfRooms = numberOfRooms;
        this.floorLevel = floorLevel;
    }

    //getters
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public int getFloorLevel() {
        return floorLevel;
    }
    
    //methods
    
}
