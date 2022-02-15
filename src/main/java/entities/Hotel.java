package entities;

import java.util.ArrayList;

public class Hotel extends Property{
    
    //attributes
    private int floorLevel;
    
    //constructors
    public Hotel () {super();}
    public Hotel(String propertyID, int floorLevel, int maxOccupancy, int sqFootage, String streetAddress, String zipCode, String city, String state, String description, String bedType, String amenities, double pricePerDay) {
        super(propertyID, maxOccupancy, sqFootage, streetAddress, zipCode, city, state, description, bedType, amenities, pricePerDay);
        this.floorLevel = floorLevel;
    }
    
    //getters
    public int getFloorLevel() {
        return floorLevel;
    }
    
    
}
