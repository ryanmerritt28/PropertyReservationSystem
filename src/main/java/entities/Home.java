package entities;

import java.util.ArrayList;

public class Home extends Property{
    
    //attributes
    private int numRooms;
    
    //constructors
    public Home() { super(); }
    public Home(String propertyID, int numRooms, int maxOccupancy, int sqFootage, String streetAddress, String zipCode, String city, String state, String description, String bedType, String amenities, double pricePerDay) {
        super(propertyID, maxOccupancy, sqFootage, streetAddress, zipCode, city, state, description, bedType, amenities, pricePerDay);
        this.numRooms = numRooms;
    }
    
    //getters
    public int getNumRooms() {
        return numRooms;
    }
    
}
