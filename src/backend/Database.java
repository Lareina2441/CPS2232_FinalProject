package backend;

import boat.Boat;
import exceptions.NotFoundByGivenInfo;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is to store all the boats and offer methods to search the boats by different attributes
 * @version 1.0
 * @since 2023-12-17
 */

public class Database implements Serializable {
    HashMap<String,ArrayList<Boat>> byAttributeBoats = new HashMap<>();
    static HashMap<Boat,Boat> allBoats = new HashMap<>();
    TreeMap<Double,ArrayList<Boat>> rPriceBoats = new TreeMap<>();
    TreeMap<Double,ArrayList<Boat>> sPriceBoats = new TreeMap<>();
    TreeMap<Integer,ArrayList<Boat>> lengthBoats = new TreeMap<>();
    ArrayList<Boat> boats = loadBoatsFromFile();
    TreeMap<Integer,ArrayList<Boat>> yearBoats = new TreeMap<>();

    public Database(){

    }

    public Database(ArrayList<Boat> boats) {
        this.boats = boats;
        //use different attribute to file the database

        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getMake(), k -> new ArrayList<>()).add(boat);//by MAKE
        }
        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getRegion(), k -> new ArrayList<>()).add(boat);//by REGION
        }
        for (Boat boat : boats
        ) {
            byAttributeBoats.computeIfAbsent(boat.getVarient(), k -> new ArrayList<>()).add(boat);//by VARIANT
        }
        for (Boat boat : boats
        ) {
            rPriceBoats.computeIfAbsent(boat.getPrice()[0], k -> new ArrayList<>()).add(boat);//by PRICE
            sPriceBoats.computeIfAbsent(boat.getPrice()[1], k -> new ArrayList<>()).add(boat);//by PRICE
        }
        for (Boat boat : boats
        ) {
            yearBoats.computeIfAbsent(boat.getYear(), k -> new ArrayList<>()).add(boat);//by PRICE}
        }
        for (Boat boat : boats
        ) {
            allBoats.put(boat,boat);
        }
        for (Boat boat : boats
        ) {
            lengthBoats.computeIfAbsent( boat.getLength(), k -> new ArrayList<>()).add(boat);//by PRICE
        }
    }

    public Boat returnNewBoat(String make, String variant, int length, String region, double sellPrice, double costPrice,double rentPrice,
                              int year){
        return new Boat(make,variant,length,region,sellPrice,costPrice,rentPrice,year,boats.size()-1);
    }

    private static void saveBoatsToFile(List<Boat> boats) {
        String filePath = "resources/createdFiles/allboats";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(boats);
            System.out.println("Boats list has been saved to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBoat(Boat boat){
        allBoats.put(boat,boat);
        byAttributeBoats.computeIfAbsent(boat.getMake(), k -> new ArrayList<>()).add(boat);//by MAKE
        byAttributeBoats.computeIfAbsent(boat.getRegion(), k -> new ArrayList<>()).add(boat);//by REGION
        byAttributeBoats.computeIfAbsent(boat.getVarient(), k -> new ArrayList<>()).add(boat);//by VARIANT
        rPriceBoats.computeIfAbsent(boat.getPrice()[0], k -> new ArrayList<>()).add(boat);//by PRICE
        sPriceBoats.computeIfAbsent(boat.getPrice()[1], k -> new ArrayList<>()).add(boat);//by PRICE
        yearBoats.computeIfAbsent(boat.getYear(), k -> new ArrayList<>()).add(boat);//by PRICE}
        lengthBoats.computeIfAbsent( boat.getLength(), k -> new ArrayList<>()).add(boat);//by PRICE
        boats.add(boat);
        saveBoatsToFile(boats);
    }

    public void deleteBoat(Boat boat){
        allBoats.remove(boat);
        byAttributeBoats.get(boat.getMake()).remove(boat);
        byAttributeBoats.get(boat.getRegion()).remove(boat);
        byAttributeBoats.get(boat.getVarient()).remove(boat);
        rPriceBoats.get(boat.getPrice()[0]).remove(boat);
        sPriceBoats.get(boat.getPrice()[1]).remove(boat);
        yearBoats.get(boat.getYear()).remove(boat);
        lengthBoats.get(boat.getLength()).remove(boat);
        boats.remove(boat);
        saveBoatsToFile(boats);
    }

    public void modifyBoat(Scanner input,Boat boat){
        System.out.println("Please input the make of the boat:");
        String make = input.next();
        System.out.println("Please input the variant of the boat:");
        String variant = input.next();
        System.out.println("Please input the length of the boat:");
        int length = input.nextInt();
        System.out.println("Please input the region of the boat:");
        String region = input.next();
        System.out.println("Please input the year of the boat:");
        int year = input.nextInt();
        System.out.println("Please input the cost price of the boat:");
        double costPrice = input.nextDouble();
        System.out.println("Please input the sell price of the boat:");
        double sellPrice = input.nextDouble();
        System.out.println("Please input the rent price of the boat:");
        double rentPrice = input.nextDouble();
        deleteBoat(boat);
        boats.remove(boat);

        addBoat(returnNewBoat(make,variant,length,region,sellPrice,costPrice,rentPrice,year));
        boats.add(boat);
        saveBoatsToFile(boats);
    }

    public static Boat searchBoat(Scanner input) throws NotFoundByGivenInfo {
        System.out.println("\n\n\nEnter any key to continue:");
        input.nextLine();
        System.out.println("Enter the information that you want to search");
        System.out.println("Format: 0:make,1:variant,2:length,3:region,4:rentPrice,5:sellPrice,6:costPrice,7:year");
        System.out.println("For example, 0:Bayliner,1:175,2:17,3:BC,4:100,5:1000,6:500,7:2018,\n     " +
                "0:Bayliner,1:175,7:2018 works \n0:seacraft,\npay attention to enter, at last \n If it still contains duplicate information, we will ask you " +
                "to offer more information");
        System.out.println("Please enter your choice:");

        String info = input.nextLine();
        String[] infos = info.split(",");
        System.out.println("Searching....: ");
        ArrayList<Boat> result = new ArrayList<>();
        //add boats by first condition, then delete the boats that do not match the second condition
        switch (infos[0].charAt(0)) {
            case '0':
                infos[0] = infos[0].substring(2);

                allBoats.forEach((k,v)->{

                    if (k.getMake().equals(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '1':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getVarient().equals(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '2':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getLength() == Double.parseDouble(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '3':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getRegion().equals(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '4':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getPrice()[0] == Double.parseDouble(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '5':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getPrice()[1] == Double.parseDouble(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '6':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getPrice()[2] == Double.parseDouble(infos[0])){
                        result.add(k);
                    }
                } );
                break;
            case '7':
                infos[0] = infos[0].substring(2);
                allBoats.forEach((k,v)->{
                    if (k.getYear() == Integer.parseInt(infos[0])){
                        result.add(k);
                    }
                } );
                break;
        }


        //delete boats that do not match the second condition
        Iterator<Boat> it = result.iterator();
        for (int i = 1; i < infos.length; i++) {
            switch (infos[i].charAt(0)) {
                case '0':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (!boat.getMake().equals(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '1':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (!boat.getVarient().equals(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '2':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (boat.getLength() != Double.parseDouble(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '3':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (!boat.getRegion().equals(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '4':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (boat.getPrice()[0] != Double.parseDouble(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '5':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (boat.getPrice()[1] != Double.parseDouble(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '6':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (boat.getPrice()[2] != Double.parseDouble(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
                case '7':
                    infos[i] = infos[i].substring(2);
                    while (it.hasNext())
                    {
                        Boat boat = it.next();
                        if (boat.getYear() != Integer.parseInt(infos[i])) {
                            it.remove();
                        }
                    }
                    break;
            }

        }
        if (result.size()==0){
            throw new NotFoundByGivenInfo("No such boats by given information");
        }
        if (result.size()==1){
            System.out.println(result.get(0));
            return result.get(0);
        }
        else {
            System.out.println("We find more than one boat by your given information, please choose one of them");
            for (int i = 0; i < result.size(); i++) {
                System.out.printf(i +": "+"%-30s%-40s%-15s%-20s%-15.2f%-15.2f%-10d%n",result.get(i).getMake(),
                        result.get(i).getVarient(),result.get(i).getLength(),result.get(i).getRegion(),
                        result.get(i).getPrice()[1],result.get(i).getPrice()[2],result.get(i).getYear());
            }
            System.out.println("Please enter your choice:");
            int choice = Integer.parseInt(input.nextLine());
            return result.get(choice);
        }

    }


    public void showAllMakes(){
        Set<Boat> makes = allBoats.keySet();
        int count = 0;
        HashMap<String,Integer> makeCount = new HashMap<>();

        for (Boat make : makes
        ) {
            if (!makeCount.containsKey(make.getMake())){
                makeCount.put(make.getMake(),1);//count the number of each make
            }
            else {
                makeCount.put(make.getMake(),makeCount.get(make.getMake())+1);
            }
        }
        for (String make : makeCount.keySet()
        ) {
            count++;
            System.out.print("|" + makeCount.get(make) + " items for "+ make + "| ");
            if (count%5==0){
                System.out.println();
            }
        }
    }

    //show rent price
    public void showAllrPrice(){
        TreeMap <Double,ArrayList<Boat>> rPrice = rPriceBoats;
        int count = 0;
        HashMap<String,Integer> makeCount = new HashMap<>();
        rPrice.forEach((k,v)->{
            int sum = v.size();
            System.out.print("| " +sum +" items at " + k +" $  each | ");
        });
    }

    //show sell price
    public void showAllsPrice(){
        TreeMap <Double,ArrayList<Boat>> sPrice = sPriceBoats;
        int count = 0;
        HashMap<String,Integer> makeCount = new HashMap<>();
        sPrice.forEach((k,v)->{
            int sum = v.size();
            System.out.print("| " +sum +" items at " + k +" $  each | ");
        });
    }


    //get average sell price by make
    public double getAveragePrice(String make){
        ArrayList<Boat> boats = byAttributeBoats.get(make);
        double sum = 0;
        for (Boat boat : boats
        ) {
            sum += boat.getPrice()[1];
        }
        return sum/boats.size();
    }

    //helper method for showBoatsByAttribute
    public void show(Scanner input)  {

        System.out.println("If you want to show all the boats, please enter 1");
        System.out.println("If you want to show the boats by make, please enter 2");
        System.out.println("If you want to show the boats by region, please enter 3");
        System.out.println("If you want to show the boats by variant, please enter 4");
        System.out.println("If you want to show the boats by rent price, please enter 5");
        System.out.println("If you want to show the boats by sell price, please enter 6");
        System.out.println("If you want to show the boats by year, please enter 7");
        System.out.println("If you want to show the boats by price range, please enter 8");
        System.out.println("If you want to show the boats by length range, please enter 9");
        System.out.println("If you want to show the boats by year range, please enter 10");
        System.out.println("If you want to show the boats by make with a largest cost please enter 11");
        System.out.println("If you want to exit, please enter 0");
        System.out.println("Please enter your choice:");
        boolean valid = false;
        do {
            try{
                int i = Integer.parseInt(input.nextLine());
                switch (i) {
                    case 1:
                        showAllBoats();
                        break;
                    case 2:
                        System.out.println("Please enter the make:");
                        String make = input.nextLine();
                        showBoatsByMake(make);
                        break;
                    case 3:
                        System.out.println("Please enter the region:");
                        String region = input.nextLine();
                        showBoatsByRegion(region);
                        break;
                    case 4:
                        System.out.println("Please enter the variant:");
                        String variant = input.nextLine();
                        showBoatsByVariant(variant);
                        break;
                    case 5:
                        System.out.println("Please enter the rent price:");
                        double rPrice = Double.parseDouble(input.nextLine());
                        showBoatsByrPrice(rPrice);
                        break;
                    case 6:
                        System.out.println("Please enter the sell price:");
                        double sPrice = Double.parseDouble(input.nextLine());
                        showBoatsBysPrice(sPrice);
                        break;
                    case 7:
                        System.out.println("Please enter the year:");
                        int year = Integer.parseInt(input.nextLine());
                        showBoatsByYear(year);
                        break;
                    case 8:
                        System.out.println("Please enter the min price:");
                        double minPrice = Double.parseDouble(input.nextLine());
                        System.out.println("Please enter the max price:");
                        double maxPrice = Double.parseDouble(input.nextLine());
                        showBoatsByPriceRange(minPrice, maxPrice);
                        break;
                    case 9:
                        System.out.println("Please enter the min length:");
                        double minLength = Double.parseDouble(input.nextLine());
                        System.out.println("Please enter the max length:");
                        double maxLength = Double.parseDouble(input.nextLine());
                        showBoatsByLengthRange(minLength, maxLength);
                        break;
                    case 10:
                        System.out.println("Please enter the min year:");
                        int minYear = Integer.parseInt(input.nextLine());
                        System.out.println("Please enter the max year:");
                        int maxYear = Integer.parseInt(input.nextLine());
                        showBoatsByYearRange(minYear, maxYear);
                        break;
                    case 11:
                        System.out.println("Please enter the price:");
                        double price = Double.parseDouble(input.nextLine());
                        System.out.println("Please enter the make:");
                        String make1 = input.nextLine();
                        showBoatsBysPriceAndMake(price, make1);
                        break;
                    case 0:
                        break;

                }
                valid = true;
            }catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
            catch (NullPointerException e){//it's not a good idea to use null pointer exception, but It works here
                // cause nothing gonna cause null pointer exception here, and define a new exception is too much work
                System.out.println("If you want to show all the boats, please enter 1");
                System.out.println("If you want to show the boats by make, please enter 2");
                System.out.println("If you want to show the boats by region, please enter 3");
                System.out.println("If you want to show the boats by variant, please enter 4");
                System.out.println("If you want to show the boats by rent price, please enter 5");
                System.out.println("If you want to show the boats by sell price, please enter 6");
                System.out.println("If you want to show the boats by year, please enter 7");
                System.out.println("If you want to show the boats by price range, please enter 8");
                System.out.println("If you want to show the boats by length range, please enter 9");
                System.out.println("If you want to show the boats by year range, please enter 10");
                System.out.println("If you want to show the boats by price and make, please enter 11");
                System.out.println("If you want to exit, please enter 0");
                System.out.println("Please enter your choice:");
                System.out.println("We do not have this boat by your GIVEN INFORMATION,please enter 0 to exit or do the " +
                        "search again");
            }
            catch (NotFoundByGivenInfo e){
                System.out.println(e+"\n\n\n");
                System.out.println("If you want to show all the boats, please enter 1");
                System.out.println("If you want to show the boats by make, please enter 2");
                System.out.println("If you want to show the boats by region, please enter 3");
                System.out.println("If you want to show the boats by variant, please enter 4");
                System.out.println("If you want to show the boats by rent price, please enter 5");
                System.out.println("If you want to show the boats by sell price, please enter 6");
                System.out.println("If you want to show the boats by year, please enter 7");
                System.out.println("If you want to show the boats by price range, please enter 8");
                System.out.println("If you want to show the boats by length range, please enter 9");
                System.out.println("If you want to show the boats by year range, please enter 10");
                System.out.println("If you want to show the boats by price and make, please enter 11");
                System.out.println("If you want to exit, please enter 0");
                System.out.println("Please enter your choice:");
                System.out.println("We do not have this boat by your GIVEN INFORMATION,please enter 0 to exit or do the " +
                        "search again");
            }
        }
        while (!valid);

    }




    /*
    From now till the end of the blocks of methods, are the methods to show the boats by different attributes.
    we offer a helper methods up here, so you do need to look at the methods below.
     */
    public void showAllBoats(){
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : allBoats.keySet()
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }
    public void showBoatsByMake(String make){
        ArrayList<Boat> boats = byAttributeBoats.get(make);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }

    public void showBoatsByRegion(String region){
        ArrayList<Boat> boats = byAttributeBoats.get(region);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }

    public void showBoatsByVariant(String variant){
        ArrayList<Boat> boats = byAttributeBoats.get(variant);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }

    public void showBoatsByrPrice(double price){
        ArrayList<Boat> boats = rPriceBoats.get(price);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }

    public void showBoatsBysPrice(double price){
        ArrayList<Boat> boats = sPriceBoats.get(price);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }

    public void showBoatsByYear(int year){
        ArrayList<Boat> boats = yearBoats.get(year);
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : boats
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }
    }
    public void showBoatsByPriceRange(double minPrice, double maxPrice) throws NotFoundByGivenInfo {
        TreeMap<Double,ArrayList<Boat>> boats = sPriceBoats;
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        AtomicBoolean foundMatchingEntries = new AtomicBoolean(false);
        //why use atomic boolean? because we need to use it in the lambda expression
        // and boolean is forbidden in lambda expression
        Set<Map.Entry<Double, ArrayList<Boat>> > entries
                = sPriceBoats.entrySet();
        entries.forEach(entry -> {
            if (entry.getKey()>=minPrice&&entry.getKey()<=maxPrice){
                foundMatchingEntries.set(true);
                ArrayList<Boat> boat = entry.getValue();

                for (Boat b : boat
                ) {System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",b.getMake(),
                        b.getVarient(),b.getLength(),b.getRegion(),
                        b.getPrice()[1],b.getPrice()[2],b.getYear());

                }
            }
        });
        if (!foundMatchingEntries.get()) {
            throw new NotFoundByGivenInfo("No such boats by given information");
        }
    }

    public void showBoatsByLengthRange(double minLength, double maxLength) throws NotFoundByGivenInfo {
        TreeMap<Integer, ArrayList<Boat>> lengthRange =  lengthBoats;
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        AtomicBoolean foundMatchingEntries = new AtomicBoolean(false);
        //why use atomic boolean? because we need to use it in the lambda expression
        // and boolean is forbidden in lambda expression
        Set<Map.Entry<Integer, ArrayList<Boat>>> entries
                = lengthRange.entrySet();
        entries.forEach(entry -> {
            if (entry.getKey()>=minLength&&entry.getKey()<=maxLength){
                ArrayList<Boat> boat = entry.getValue();
                foundMatchingEntries.set(true);
                for (Boat b : boat
                ) {
                    System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",b.getMake(),
                            b.getVarient(),b.getLength(),b.getRegion(),
                            b.getPrice()[1],b.getPrice()[2],b.getYear());
                }
            }
        });
        if (!foundMatchingEntries.get()) {
            throw new NotFoundByGivenInfo("No such boats by given information");
        }
    }

    public void showBoatsByYearRange(double minYear, double maxYear) throws NotFoundByGivenInfo {
        TreeMap<Integer,ArrayList<Boat>> boats = yearBoats;
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        AtomicBoolean foundMatchingEntries = new AtomicBoolean(false);
        //why use atomic boolean? because we need to use it in the lambda expression
        // and boolean is forbidden in lambda expression
        Set<Map.Entry<Integer, ArrayList<Boat>> > entries
                = boats.entrySet();
        entries.forEach(entry -> {
            if (entry.getKey()>=minYear&&entry.getKey()<=maxYear) {
                ArrayList<Boat> boat = entry.getValue();
                foundMatchingEntries.set(true);
                for (Boat b : boat
                ) {
                    System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",b.getMake(),
                            b.getVarient(),b.getLength(),b.getRegion(),
                            b.getPrice()[1],b.getPrice()[2],b.getYear());
                }

            }
        });
        if (!foundMatchingEntries.get()) {
            throw new NotFoundByGivenInfo("No such boats by given information");
        }

    }
    /**
     * This method is to show the boats by make and find the price that is less than the condition
     *
     */
    public void showBoatsBysPriceAndMake(double price, String make) {
        ArrayList<Boat> boats = byAttributeBoats.get(make);
        ArrayList<Boat> result = new ArrayList<>();
        for (Boat boat : boats
        ) {
            if (boat.getPrice()[1] <= price) {
                result.add(boat);
            }
        }
        System.out.printf("%-30s%-35s%-30s%-30s%-15s%-15s%-10s%n","Make",
                "Variant","Length","Region","SellPrice","RentPrice","Year");
        for (Boat boat : result
        ) {
            System.out.printf("%-30s%-35s%-30s%-30s%-15.2f%-15.2f%-10d%n",boat.getMake(),
                    boat.getVarient(),boat.getLength(),boat.getRegion(),
                    boat.getPrice()[1],boat.getPrice()[2],boat.getYear());
        }

    }//end of the blocks of methods to show the boats by different attributes.


    public static ArrayList<Boat> loadBoatsFromFile() {
        String filePath = "resources/createdFiles/allboats";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<Boat>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Boat> getBoats() {
        return boats;
    }

    public HashMap<String, ArrayList<Boat>> getByAttributeBoats() {
        return byAttributeBoats;
    }

    public void setByAttributeBoats(HashMap<String, ArrayList<Boat>> byAttributeBoats) {
        this.byAttributeBoats = byAttributeBoats;
    }

    public static HashMap<Boat, Boat> getAllBoats() {
        return allBoats;
    }

    public static void setAllBoats(HashMap<Boat, Boat> allBoats) {
        Database.allBoats = allBoats;
    }

    public TreeMap<Double, ArrayList<Boat>> getrPriceBoats() {
        return rPriceBoats;
    }

    public void setrPriceBoats(TreeMap<Double, ArrayList<Boat>> rPriceBoats) {
        this.rPriceBoats = rPriceBoats;
    }

    public TreeMap<Double, ArrayList<Boat>> getsPriceBoats() {
        return sPriceBoats;
    }

    public void setsPriceBoats(TreeMap<Double, ArrayList<Boat>> sPriceBoats) {
        this.sPriceBoats = sPriceBoats;
    }

    public TreeMap<Integer, ArrayList<Boat>> getLengthBoats() {
        return lengthBoats;
    }

    public void setLengthBoats(TreeMap<Integer, ArrayList<Boat>> lengthBoats) {
        this.lengthBoats = lengthBoats;
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public TreeMap<Integer, ArrayList<Boat>> getYearBoats() {
        return yearBoats;
    }

    public void setYearBoats(TreeMap<Integer, ArrayList<Boat>> yearBoats) {
        this.yearBoats = yearBoats;
    }
}