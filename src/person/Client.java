package person;

import interfaces.Person;
/*
    * This class is used to define the methods that all clients must have.
    * A client is a person who can borrow or buy a boat.
    * its priority, name and password can be modified and got.
    *
 */
public class Client implements Person {
    private int priority;
    private String name;
    private String password;
    private String uniqueID;

    public Client(int priority, String name, String password) {
        this.priority = priority;
        this.name = name;
        this.password = password;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public int getPriority() {
        return priority;
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
                "priority=" + priority +
                ", name='" + name + '\'' +
                ", password=" + password +
                '}';
    }
}
