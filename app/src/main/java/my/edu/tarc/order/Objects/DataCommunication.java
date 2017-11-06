package my.edu.tarc.order.Objects;

import my.edu.tarc.order.MainActivity;

/**
 * Created by Leow on 10/31/2017.
 */

public interface DataCommunication {
    public String getCanteen();
    public void setCanteen(String canteenName);

    public String getStall();
    public void setStall(String stallName);
    public String getStudID();
    public void setStudID(String studID);
    public int getProdID();
    public void setProdID(int prodID);
    public void setOrderDesc(int orderID, String productName, double Total, String orderStatus, String orderDateTime);
}
