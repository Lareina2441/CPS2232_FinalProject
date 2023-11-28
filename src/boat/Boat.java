package boat;

import interfaces.Asset;

public class Boat implements Asset {
    public String maker;
    public String variant;
    public int length;
    public String region;
    public int sellPrice;
    public int costPrice;
    public int rentPrice;
    public int year;
    private int[] prices = new int[3];
    private Object owner;
    private Object user;


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
    public Object getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    @Override
    public void setOwner(Object owner) {
        this.owner = owner;

    }

    /**
     * @return
     */
    @Override
    public Object getUser() {
        return user;
    }

    /**
     * @param user
     */
    @Override
    public void setUser(Object user) {
        this.user = user;
    }
}