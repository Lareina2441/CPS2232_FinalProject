package CPS2232.FinalProject;

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

/*    Comparator<Client> comparator = new Comparator<Client>() {
        @Override
        public int compare(Client c1, Client c2) {
            return c1.credit().compareTo(c2.credit());
        }
    };*/
    private String uniqueID;

    public Client(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
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
}
