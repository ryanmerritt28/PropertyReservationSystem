package entities;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class Test {

    public static void main(String[] args) throws CustomerNotFoundException {
        
        Customer c = Customer.getCustomer("1");
        System.out.println(c.toString());
        
        List<Property> props = Property.getProperties();
        for (Property p : props){
            System.out.println(p.toString());
        }
        
    }
    
}
