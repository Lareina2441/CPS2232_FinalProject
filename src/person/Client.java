package person;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import boat.Boat;
import interfaces.Person;

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
    private HashSet<Boat> own = new HashSet<Boat>();
    private HashSet<Boat> use = new HashSet<Boat>();

    /*    Comparator<Client> comparator = new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.credit().compareTo(c2.credit());
            }
        };*/
    private String uniqueID;

    public Client(String name, String password, String email, HashSet<Boat> use, HashSet<Boat> own) {
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

    public HashSet<Boat> getOwn() {
        return own;
    }

    public void setOwn(HashSet<Boat> own) {
        this.own = own;
    }

    public HashSet<Boat> getUse() {
        return use;
    }

    public void setUse(HashSet<Boat> use) {
        this.use = use;
    }
}
