package boat;

import interfaces.Asset;
import interfaces.Person;

public class Boat implements Asset,Comparable<Boat> {
    private String make;
    private String variant;
    private double length;
    private String region;
    private double sellPrice;
    private double costPrice;
    private double rentPrice;
    private double year;
    private double[] prices = new double[3];
    private Person owner;
    private Person user;


    public Boat(String make, String variant, double length, String region, double sellPrice, double costPrice,double rentPrice,
                double year) {
        this.make = make;
        this.variant = variant;
        this.length = length;
        this.region = region;
        this.sellPrice = sellPrice;
        this.year = year;
        prices[0] = rentPrice;
        prices[1] = sellPrice;
        prices[2] = costPrice;

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
        this.rentPrice = rentPrice;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;

        prices[0] = rentPrice;
        prices[1] = sellPrice;
        prices[2] = costPrice;
    }

    /**
     * @return
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
     * @return
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

    public double getLength() {
        return length;
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
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    /**
     * @param o the object to be compared.
     * @return
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
}
