package person;

import boat.Boat;
import interfaces.Person;

import java.io.Serializable;
import java.util.HashSet;

public class Client implements Person, Serializable {

    //  This class describes the attributes and operations of clients.
    private String name;
    private String password;
    private String email;
    //  The credit of the client depends on the money that the client has paid.
    private int credit;
    //  The appointment is the set of boats that the client has booked.
    private HashSet<Boat> appointment = new HashSet<Boat>();
    //  The own is the set of boats that the client has bought.
    private HashSet<Boat> own = new HashSet<Boat>();

    private String uniqueID;

    public Client(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     *
     */
    @Override
    public void setPassword(String password) {
        this.password = password;

    }
}


