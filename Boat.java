package CPS2232.FinalProject;

public class Boat {
    public String make;
    public String varient;
    public int length;
    public String region;
    public int borrowPrice;
    public int sellPrice;
    public int year;

    public Boat(String make, String varient, int length, String region, int sellPrice, int year) {
        this.make = make;
        this.varient = varient;
        this.length = length;
        this.region = region;
        this.sellPrice = sellPrice;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getVarient() {
        return varient;
    }
}
