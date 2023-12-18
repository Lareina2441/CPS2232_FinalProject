package person;


import backend.Logger;
import boat.Boat;
import interfaces.Person;

import javax.mail.MessagingException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class describes the attributes and operations of clients.
 * @version 1.0
 * @since 2023-12-17
 *
 */
public class Client implements Person, Serializable {

    //  This class describes the attributes and operations of clients.
    private String name;
    private String password;
    private String email;
    private ArrayList transaction;


    //  The own is the set of boats that the client has bought.
    private ArrayList<Boat> own ;

    //  The use is the set of boats that the client has rented.
    private ArrayList<Boat> use ;

    private Logger logger = new Logger(this);

    private String uniqueID;

    public Client(String name, String password, String email, ArrayList<Boat> use, ArrayList<Boat> own) throws MessagingException {
        this.name = name;
        this.password = password;
        this.email = email;
        this.use = use;
        this.own = own;
    }

    public String getEmail() {
        return email;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void setName() {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword() {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", name='" + name + '\'' +
                ", email=" + email +
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