package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.IPersistable;

public class Reservation implements Serializable, IPersistable {
    
    //attributes
    private int reservationID;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numGuests;
    private Property property;
    private Customer customer;
    private static ArrayList<Reservation> allReservations = new ArrayList<>();
    //private static int count = 1;
    private static final String fileName = "Reservations.bin";
    
    //constructors
    public Reservation () {}
    public Reservation(LocalDate checkIn, LocalDate checkOut, Property property, Customer customer) { 
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        //this.numGuests = numGuests
        this.property = property;
        this.customer = customer;
        //reservationID = count++; 
        property.addPropReservation((this));
        customer.addCustReservation((this));
        Reservation.addReservation((this));
    } 
    
//getters and setters
    public int getReservationID() {
        return reservationID;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public Property getProperty() {
        return property;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static void setAllReservations(ArrayList<Reservation> allReservations) {
        Reservation.allReservations = allReservations;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }
    
    //methods
    public void deleteReservation(Reservation resToDelete) {
        resToDelete = null;
    }
    
    public static void addReservation(Reservation r) {
        allReservations.add(r);
    }
    
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s", reservationID, checkIn, checkOut, property.toString(), customer.toString());
    }

   @Override
    public int Persist() {
        List<Reservation> pastReservations = Reservation.getReservations();
        if (pastReservations != null) {
            reservationID = pastReservations.get(pastReservations.size() - 1).getReservationID() + 1;
        } else {
            reservationID = 1;
        }

        ObjectOutputStream oos = null;
        try {
            String fn = String.format("%s%s", IPersistable.path, fileName);
            oos = new ObjectOutputStream(new FileOutputStream(fn)); //this clears all existing
            if (pastReservations != null) {
                for (Reservation res : pastReservations) {
                    oos.writeObject(res);
                }
            }
            oos.writeObject(this);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return reservationID;
    }
    
    public static List<Reservation> getReservations() {

        List<Reservation> allRes = null;
        ObjectInputStream ois = null;
        try {
            String fn = String.format("%s%s", IPersistable.path, fileName);
            FileInputStream fis = new FileInputStream(fn);
            ois = new ObjectInputStream(fis);
            boolean hasnext = (fis.available() != 0);

            while (hasnext) {

                Reservation r = (Reservation) ois.readObject();
                if (r != null) {
                    if (allRes == null) {
                        allRes = new ArrayList<>();
                    }
                    allRes.add(r);
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
        return allRes;
    }
}
    
    
