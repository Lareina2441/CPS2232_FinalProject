package person;

import interfaces.Person;

import java.io.Serializable;

public class Company implements Person, Serializable {
    private String name;
    private String password;

    public Company(int priority, String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }



    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    @Override
    public void setPassword(String password) {
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

