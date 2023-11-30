package calculator.A.FInal;

import java.util.PriorityQueue;

public class Boat {
    //  The data field of the boat is the same as the title 
    //  of the the columns in the csv file
    private String make;
    private String variant;
    private double length;
    private String country;
    private Double listingPrice;
    private int year;

    // This queue describes the priority queue of this specific boat.
    private PriorityQueue<Client> clientList = new PriorityQueue<Client>();

    // The constructor of the boat class
    public Boat(String make, String variant, double length, String country, Double listingPrice, int year) {
        this.make = make;
        this.variant = variant;
        this.length = length;
        this.country = country;
        this.listingPrice = listingPrice;
        this.year = year;
        this.clientList = null;
    }
}
