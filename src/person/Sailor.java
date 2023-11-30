package person;

import interfaces.Person;
/*
    * This class is used to define the methods that all sailors must have.
    * A sailor is a person who can drive and manage a boat.
    * If a boat belong to the sailor (Represent our company), the boat could be sold or rented.
    *
    *
    *
 */
public class Sailor implements Person {
    private String name;
    private String password;

    public Sailor(int priority, String name, String password) {
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
