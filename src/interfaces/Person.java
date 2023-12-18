package interfaces;
/**
    * This interface is used to define the methods that all clients must have.
    * A client is a person who can borrow or buy a boat.
     * @version 1.0
     * @since 2023-12-17
    *
 */

import java.io.Serializable;

public interface Person extends Serializable {
     String getName();
     void setName();
     String getPassword();
     void setPassword();
     String getEmail();

}