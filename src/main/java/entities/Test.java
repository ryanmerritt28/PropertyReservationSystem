package entities;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class Test {

    public static void main(String[] args) throws CustomerNotFoundException {
        
        Customer c = new Customer ("4", "Ryan", "Merritt", "address", "email", "phone");
        Property p = new Property("8", 5, 650, "address", "zip", "city", "state", "desc", "bed", "amen", 789.13);
        
        Reservation r = new Reservation(LocalDate.parse("2020-01-06"), LocalDate.parse("2020-01-09"), p, c);
        r.setReservationID(r.Persist());
    }
    
}
