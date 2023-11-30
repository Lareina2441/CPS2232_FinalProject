package calculator.A.FInal;

import java.util.Comparator;

public class Client{
    //  This class describes
    // the attributes and operations of clients.
    private String name;
    private String password;
    private String email;
    
    Comparator<Client> comparator = new Comparator<Client>() {
        @Override
        public int compare(Client c1, Client c2) {
            return c1.getName().compareTo(c2.getName());
        }
    };

    public Client(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    

}

