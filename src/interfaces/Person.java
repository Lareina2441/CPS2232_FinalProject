package interfaces;
/*
    * This interface is used to define the methods that all clients must have.
    * A client is a person who can borrow or buy a boat.
    *
 */

import java.io.Serializable;

public interface Person extends Serializable {
     String getName();
     void setName(String name);
     String getPassword();
     void setPassword(String password);

}