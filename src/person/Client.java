package person;


import boat.Boat;
import interfaces.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Client implements Person, Serializable {

    //  This class describes the attributes and operations of clients.
    private String name;
    private String password;
    private String email;

    private ArrayList transaction;    //  The credit of the client depends on the money that the client has paid.
    private String log;

    //  The appointment is the set of boats that the client has booked.
    private HashSet<Boat> appointment = new HashSet<Boat>();
    //  The own is the set of boats that the client has bought.
    private ArrayList<Boat> own ;
    private ArrayList<Boat> use ;

    /*    Comparator<Client> comparator = new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.credit().compareTo(c2.credit());
            }
        };*/
    private String uniqueID;

    public Client(String name, String password, String email, ArrayList<Boat> use, ArrayList<Boat> own) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.use = use;
        this.own = own;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    /**
     *
     */
    @Override
    public void setName() {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    /**
     *
     */
    @Override
    public void setPassword() {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }

    public ArrayList getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList transaction) {
        this.transaction = transaction;
    }

    public ArrayList<Boat> getOwn() {
        if(own == null){
            own = new ArrayList<>();
        }
        return own;
    }

    public void setOwn(ArrayList<Boat> own) {
        this.own = own;
    }

    public ArrayList<Boat> getUse() {
        if(use == null){
            use = new ArrayList<>();
        }
        return use;
    }


    public void setUse(ArrayList<Boat> use) {
        this.use = use;
    }
}