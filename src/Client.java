package calculator.A.FInal;

import java.util.Comparator;
import java.util.HashSet;

public class Client{
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

    Comparator<Client> comparator = new Comparator<Client>() {
        @Override
        public int compare(Client c1, Client c2) {
            return c1.credit().compareTo(c2.credit());
        }
    };

    public Client(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.credit = 0;

    }
    public String getEmail(String name) {
        return email;
    }



    

}

