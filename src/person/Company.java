package person;
import java.io.Serializable;
import interfaces.Person;

public class Company implements Person, Serializable {
    private String name;
    private String password;
    String email = "zhaoq@kean.edu";

    public Company(int priority, String name, String password) {
        this.name = name;
        this.password = password;
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

    public String getEmail() {

        return email;
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
        return "Sailor{" +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}

