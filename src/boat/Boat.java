package boat;

import interfaces.Asset;
import interfaces.Person;

public class Boat implements Asset {
    private String maker;
    private String variant;
    private int length;
    private String region;
    private int sellPrice;
    private int costPrice;
    private int rentPrice;
    private int year;
    private int[] prices = new int[3];
    private Person owner;
    private Person user;
    private String uniqueID;


    public Boat(String maker, String variant, int length, String region, int sellPrice, int costPrice,int rentPrice,
                int year) {
        this.maker = maker;
        this.variant = variant;
        this.length = length;
        this.region = region;
        this.sellPrice = sellPrice;
        this.year = year;
        prices[0] = rentPrice;
        prices[1] = sellPrice;
        prices[2] = costPrice;

    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getMake() {
        return maker;
    }

    public String getVarient() {
        return variant;
    }

    /**
     * @return
     */
    @Override
    public int[] getPrice() {
        return prices;
    }

    /**
     *
     */
    @Override
    public void setPrice(int rentPrice,int sellPrice,int costPrice) {
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
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getLength() {
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

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}