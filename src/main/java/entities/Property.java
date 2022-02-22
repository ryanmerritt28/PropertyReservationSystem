package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.time.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import util.IPersistable;

public class Property implements Serializable, IPersistable {
    
    //attributes
    private String propertyID;
    private int maxOccupancy;
    private int sqFootage;
    private String streetAddress;
    private String zipCode;
    private String city;
    private String state;
    private String description;
    private String bedType;
    private String amenities;
    private double pricePerDay;
    //private boolean available;
    private List<Reservation> propReservations;
    //private static int count = 1;
    private static final String fileName = "Properties.csv";
    private static final long serialVersionUID = 2760193673477602867L;

    //constructors
    public Property () {}
    public Property(String propertyID, int maxOccupancy, int sqFootage, String streetAddress, String zipCode, String city, String state, String description, String bedType, String amenities, double pricePerDay) {
        this.propertyID = propertyID;
        this.maxOccupancy = maxOccupancy;
        this.sqFootage = sqFootage;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.description = description;
        this.bedType = bedType;
        this.amenities = amenities;
        this.pricePerDay = pricePerDay;
        //this.available = available;
        propReservations = new ArrayList<>();
    }
    
    //getters and setters
    public String getPropertyID() {
        return propertyID;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public int getSqFootage() {
        return sqFootage;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getBedType() {
        return bedType;
    }

    public String getAmenities() {
        return amenities;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    //public boolean isAvailable() {
    //    return available;
    //}

    public List<Reservation> getReservations() {
        return propReservations;
    }

    //public void setAvailable(boolean available) {
     //   this.available = available;
    //}

    //methods
    @Override
    public String toString() {
        String rv = String.format("%s. %s, Amenities: %s", propertyID, streetAddress, amenities);
        
        return rv;
    }
    
    public void addPropReservation(Reservation r) {
        propReservations.add(r);  
    }
    
    public boolean checkAvailability(LocalDate in, LocalDate out) {
        for (Reservation r : Property.getPropReservations(propertyID)) {
                return !(in.isBefore(r.getCheckOut()) && out.isAfter(r.getCheckIn()));
            }
        return true;
    }
    
    @Override
    public int Persist() {
        return 1;
    }
    
    public static List<Property> getProperties()  {
        Scanner s = null;
        Property p = null;
        List<Property> properties = null;
        
        String file = String.format("%s%s", IPersistable.path, fileName);
        
        try {
            s = new Scanner(new FileInputStream(file));
            while (s.hasNext()) {
                String[] vals = s.nextLine().split(",");
                int length = vals.length;
                if (vals[length -1].equals(String.valueOf(2))) {
                    if (properties == null) {
                        properties = new ArrayList<>();
                    }
                    p = new Condo(vals[0],Integer.parseInt(vals[1]),Integer.parseInt(vals[2]),Integer.parseInt(vals[3]),Integer.parseInt(vals[4]),vals[5],vals[6],vals[7],vals[8],vals[9],vals[10],vals[11],Double.parseDouble(vals[12]));
                    properties.add(p);
                }
                else if (vals[length-1].equals(String.valueOf(3))) {
                    if (properties == null) {
                        properties = new ArrayList<>();
                    }
                    p = new Home(vals[0],Integer.parseInt(vals[1]),Integer.parseInt(vals[2]),Integer.parseInt(vals[3]),vals[4],vals[5],vals[6],vals[7],vals[8],vals[9],vals[10],Double.parseDouble(vals[11]));
                    properties.add(p);
                }
                else if (vals[length-1].equals(String.valueOf(4))){
                    if (properties == null) {
                        properties = new ArrayList<>();
                    }
                    p = new Hotel(vals[0],Integer.parseInt(vals[1]),Integer.parseInt(vals[2]),Integer.parseInt(vals[3]),vals[4],vals[5],vals[6],vals[7],vals[8],vals[9],vals[10],Double.parseDouble(vals[11]));
                    properties.add(p);
                }
            }
             
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        finally {
            if (s != null) {
                s.close();
            }
        }
        return properties;
    }
        
    public static List<Reservation> getPropReservations(String propertyID) {
        List<Reservation> propRes = null;
        ObjectInputStream ois = null;
        try {
            String fn = String.format("%s%s", IPersistable.path, "Reservations.bin");
            FileInputStream fis = new FileInputStream(fn);
            ois = new ObjectInputStream(fis);
            boolean hasnext = (fis.available() != 0);

            while (hasnext) {

                Reservation r = (Reservation) ois.readObject();
                if (r != null) {
                    if (propRes == null) {
                        propRes = new ArrayList<>();
                    }
                    if (propertyID.equals(r.getProperty().getPropertyID())) {
                        propRes.add(r);
                    }
                }
                hasnext = (fis.available() != 0);
            }
        } catch (FileNotFoundException fnfe) {
            String msg = fnfe.getMessage();
            System.out.println("Warrning: " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ios) {
                ios.printStackTrace();
            }
        }
        return propRes;
    }
  
   public boolean containsSelectedAmenities(Property p, String toSearch) {
       List<String> propAmenities = Arrays.asList(p.getAmenities().split(" "));
       List<String> search = Arrays.asList(toSearch.split(" "));
       
       return propAmenities.containsAll(search);
   }
    
}
