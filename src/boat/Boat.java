package boat;

import interfaces.Asset;

public class Boat implements Asset {
    public String make;
    public String variant;
    public int length;
    public String region;
    public int borrowPrice;
    public int sellPrice;
    public int year;

    public Boat(String make, String variant, int length, String region, int sellPrice, int year) {
        this.make = make;
        this.variant = variant;
        this.length = length;
        this.region = region;
        this.sellPrice = sellPrice;
        this.year = year;
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
    public int[] getPrice() {
        return new int[0];
    }

    /**
     * @param price
     */
    @Override
    public void setPrice(String price) {

    }

    /**
     * @return
     */
    @Override
    public Object getOwner() {
        return null;
    }

    /**
     * @param owner
     */
    @Override
    public void setOwner(Object owner) {

    }

    /**
     * @return
     */
    @Override
    public Object getUser() {
        return null;
    }

    /**
     * @param user
     */
    @Override
    public void setUser(Object user) {

    }
}