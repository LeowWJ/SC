package my.edu.tarc.order.Objects;

import my.edu.tarc.order.MainActivity;

/**
 * Created by Leow on 10/31/2017.
 */

public interface DataCommunication {
    String getCanteen();
    void setCanteen(String canteenName);

    String getStall();
    void setStall(String stallName);
    String getStudID();
    void setStudID(String studID);
    int getProdID();
    void setProdID(int prodID);
    void setOrderDesc(int orderID, String productName, double Total, String orderStatus, String orderDateTime);
}
