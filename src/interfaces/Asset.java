package interfaces;

/*
 * This interface is used to define the methods that all assets must have.
 * This interface is implemented by the Boat class.
 *
 * An asset is anything that can be bought, sold or borrowed.
 * its price, owner and user can be modified and got.
 *
*/

import java.io.Serializable;

public interface Asset extends Serializable {
    //return an array to store the information of the asset,arr[0] is the rent price, arr[1] is the sell price,
    // arr[2] is the cost price,
    double[] getPrice();
    void setPrice(int rentPrice,int sellPrice,int costPrice);
    Person getOwner();
    void setOwner(Person owner);
    Person getUser();
    void setUser(Person user);


}
