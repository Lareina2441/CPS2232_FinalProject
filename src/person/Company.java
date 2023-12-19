package person;

import interfaces.Person;

import java.io.Serializable;

/**
 * This class describes the attributes and operations of company
 * it is used just to represent as an entity.
 * @version 1.0
 * @since 2023-12-17
 *
 */

public class Company implements Person, Serializable {
    private String name;
    //use this password to login company system
    private String password = "cps";
    //email to receive the system notification
    String email = "administrator email, define your own";

    public Company(int priority, String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Company() {

    }

    public String getName() {
        return name;
    }

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

    @Override
    public void setPassword() {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Company{" +
                ", name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}

