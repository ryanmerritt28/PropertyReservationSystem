package entities;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import util.IPersistable;

public class Customer {//implements Serializable, IPersistable {
    
    //attributes
    private String fname;
    private String lname;
    private String address;
    private String email;
    private String phone;
    private String customerID;
    private ArrayList<Reservation> custReservations;
    private static final String fileName = "Customers.csv";
    
    //constructors
    public Customer () {}
    public Customer (String customerID, String fname, String lname, String address, String email, String phone) {
        
        this.customerID = customerID;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        custReservations = new ArrayList<>();
    }
    
    //getters and setters
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public ArrayList<Reservation> getReservations() {
        return custReservations;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    
    
    //methods
    public void addCustReservation(Reservation r) {
        custReservations.add(r);
    }
    
//    public static Customer getCustomer(int custID) throws CustomerNotFoundException {
//        Scanner s = null;
//        Customer c = null;
//        
//        String file = String.format("%s%s", IPersistable.path, fileName);
//        
//        try {
//            s = new Scanner(new FileInputStream(file));
//            while (s.hasNext()) {
//                String[] vals = s.nextLine().split(",");
//                if (vals[0].equals(custID)) {
//                    c = new Customer(vals[0], vals[1], vals[2], vals[3], vals[4], vals[5]);
//                    break;
//                }
//            }
//             if (c == null) {
//                 throw new CustomerNotFoundException (String.format("Customer %s does not exist", custID));
//             }     
//        }
//        catch (FileNotFoundException fnfe) {
//            fnfe.printStackTrace();
//        }
//        finally {
//            if (s != null) {
//                s.close();
//            }
//        }
//        return c;
//    }
//    
//    @Override
//    public String toString() {
//        return String.format("%s%s%s%s%s%s", customerID, fname, lname, address, email, phone);
//    }
//    
//    @Override
//    public void writeToDB(String dbname) {}
//    
//    @Override
//    public void writeToFile(){}
//    
//    @Override
//    public int Persist() {
//        PrintWriter fout = null;
//        String fileName = String.format("%s%s", IPersistable.path, "Customers.csv");
//        try {
//            fout = new PrintWriter(new FileOutputStream(fileName, true));
//        }
//        catch (FileNotFoundException fnfe) {
//            fnfe.printStackTrace();
//        }
//        finally {
//            if (fout != null) {
//                fout.close();
//            }
//        }
//        return 1;
//    }
    
    
    
}