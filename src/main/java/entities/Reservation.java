package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    
    //attributes
    private int reservationID;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numGuests;
    private Property property;
    private Customer customer;
    private static ArrayList<Reservation> allReservations = new ArrayList<>();
    private static int count = 1;
    
    //constructors
    public Reservation () {}
    public Reservation(LocalDate checkIn, LocalDate checkOut, Property property, Customer customer) { 
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        //this.numGuests = numGuests
        this.property = property;
        this.customer = customer;
        reservationID = count++; 
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
    
    
    //methods
    public void deleteReservation(Reservation resToDelete) {
        resToDelete = null;
    }
    
    public static void addReservation(Reservation r) {
        allReservations.add(r);
    }
    
//    public void reserveProperty(Customer c, Property p, LocalDate checkIn, LocalDate checkOut) {
//        
//        Reservation reservation = new Reservation(checkIn, checkOut, p, c);
//        c.addCustReservation(reservation);
//        p.addPropReservation(reservation);
//        Reservation.allReservations.add(reservation);
//    }
}
    
    
