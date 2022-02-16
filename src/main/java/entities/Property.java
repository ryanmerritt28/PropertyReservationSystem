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
        String rv = String.format("%s. %s", propertyID, streetAddress);
        
        return rv;
    }
    
    public void addPropReservation(Reservation r) {
        propReservations.add(r);  
    }
    
    public boolean checkAvailability(LocalDate in, LocalDate out) {
        for (Reservation r : propReservations) {
                return !(in.isBefore(r.getCheckOut()) && out.isAfter(r.getCheckIn()));
            }
        return true;
    }
    
//    public static List<Property> getProperties() {
//        List<Property> props = null;
//        ObjectInputStream ois = null;
//        try {
//            String file = String.format("%s%s", IPersistable.path, fileName);
//            FileInputStream fis = new FileInputStream(file);
//            ois = new ObjectInputStream(fis);
//            boolean hasNext = (fis.available() != 0);
//            
//            while (hasNext) {
//                Property prop = (Property) ois.readObject();
//                if (prop != null) {
//                    if (props == null) {
//                        props = new ArrayList<>();
//                    }
//                    props.add(prop);
//                }
//                hasNext = (fis.available() != 0);
//            }
//        }
//        catch (FileNotFoundException fnfe) {
//                fnfe.printStackTrace();
//                }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                if (ois != null) {
//                    ois.close();
//                }
//            }
//            catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//        }
//        return props;
//    }
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
  
    
    
}
