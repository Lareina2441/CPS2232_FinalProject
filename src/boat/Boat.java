package boat;

import interfaces.Asset;
import interfaces.Person;
import person.Company;

import java.io.Serializable;

/**
    * This class describes the attributes and operations of boats.
    * @version 1.0
    * @since 2023-12-17
    *
 */
public class Boat implements Asset,Comparable<Boat>, Serializable {
    //
    private String make;
    private String variant;
    private int length;
    private String region;
    private double costPrice;
    private int year;
    private double[] prices = new double[3];
    private Person owner = new Company(0,"Sea Craft 2018","cps");
    private Person user = new Company(0,"Sea Craft 2018","cps");
    private int index;


    public Boat(String make, String variant, int length, String region, double costPrice, double sellPrice, double rentPrice,
                int year,int index) {
        this.make = make;
        this.variant = variant;
        this.length = length;
        this.region = region;
        this.year = year;
        this.index=index;
        prices[0] = costPrice;
        prices[1] = sellPrice;
        prices[2] = rentPrice;

    }


    public String getMake() {
        return make;
    }

    public String getVarient() {
        return variant;
    }

    /**
     * @return
     */
    @Override
    public double[] getPrice() {
        return prices;
    }

    /**
     *
     */
    @Override
    public void setPrice(double rentPrice,double sellPrice,double costPrice) {

        this.costPrice = costPrice;

        prices[0] = rentPrice;
        prices[1] = sellPrice;
        prices[2] = costPrice;
    }

    /**
     * @return owner
     */
    @Override
    public Person getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    @Override
    public void setOwner(Person owner) {
        this.owner = owner;

    }

    /**
     * @return user
     */
    @Override
    public Person getUser() {
        return user;
    }

    /**
     * @param user
     */
    @Override
    public void setUser(Person user) {
        this.user = user;
    }

    public String getMaker() {
        return make;
    }

    public void setMaker(String maker) {
        this.make = maker;
    }

    public int getLength() {
        return length;
    }

    public double getRentPrice() {
        return prices[0];
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getCostPrice() {
        return prices[0];
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }



    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    /**
     * @param o the object to be compared.
     * @return 0 if the objects are equal, a negative integer if this object is less than the other,
     */
    @Override
    public int compareTo(Boat o) {
        return (int) (this.getPrice()[1] - o.getPrice()[1]);
    }

    @Override
    public int hashCode() {
        return (int) (this.getMake().hashCode() + this.getVarient().hashCode() + this.getLength() + this.getRegion().hashCode()
                        + this.getYear() + this.getPrice()[1]);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Boat) {
            Boat boat = (Boat) obj;
            return this.getMake().equals(boat.getMake()) && this.getVarient().equals(boat.getVarient())
                    && this.getLength() == boat.getLength() && this.getRegion().equals(boat.getRegion())
                    && this.getYear() == boat.getYear() && this.getPrice()[1] == boat.getPrice()[1];
        }
        return false;
    }

    public double getSellPrice() {
        return costPrice * (30 + year * 20) / 30000;
    }


    @Override
    public String toString() {
        return String.format("Make: %-20sVariant: %-20sLength: %-20dRegion: %-10sYear: %-5dCost Price: %-15.2fSell Price: %-15.2f" +
                        "Rent Price: %-15.2fOwner: %s    User: %s\n", make, variant, length, region, year,
                prices[0], prices[1], prices[2],owner.getName(),user.getName());
    }

    public Boat(){};

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMake(String make) {
    }

    public void setVarient(String variant) {
    }
}