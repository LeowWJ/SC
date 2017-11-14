package my.edu.tarc.order.Objects;

import java.sql.Timestamp;

/**
 * Created by Leow on 11/1/2017.
 */

public class Order {
    private int orderID;
    private String walletID;
    private String prodID;
    private String prodName;
    private String orderDateTime;
    private int orderQuantity;
    private String orderStatus;
    private double payAmount;
    private String payDateTime;

    public Order(){

    }

    public Order(String walletID, String prodID, String orderDateTime, int orderQuantity,
                 double payAmount){
        this.setWalletID(walletID);
        this.setProdID(prodID);
        this.setOrderDateTime(orderDateTime);
        this.setOrderQuantity(orderQuantity);
        this.setOrderStatus("pending");
        this.setPayAmount(payAmount);
        this.setPayDateTime(null);
    }

    public Order(int orderID, String prodName, int orderQuantity, double payAmount,
                 String orderStatus, String orderDateTime){
        this.setOrderID(orderID);
        this.setWalletID(walletID);
        this.setProdName(prodName);
        this.setOrderDateTime(orderDateTime);
        this.setOrderQuantity(orderQuantity);
        this.setOrderStatus(orderStatus);
        this.setPayAmount(payAmount);
        this.setPayDateTime(null);
    }

    public Order(int orderID, String prodName, int orderQuantity, double payAmount,
                 String orderStatus, String orderDateTime, String payDateTime){
        this.setOrderID(orderID);
        this.setWalletID(walletID);
        this.setProdName(prodName);
        this.setOrderDateTime(orderDateTime);
        this.setOrderQuantity(orderQuantity);
        this.setOrderStatus(orderStatus);
        this.setPayAmount(payAmount);
        this.setPayDateTime(payDateTime);
    }

    public Order(int orderID, String walletID, String prodID, String orderDateTime,
                 int orderQuantity, String orderStatus, double payAmount, String payDateTime){
        this.setOrderID(orderID);
        this.setWalletID(walletID);
        this.setProdID(prodID);
        this.setOrderDateTime(orderDateTime);
        this.setOrderQuantity(orderQuantity);
        this.setOrderStatus(orderStatus);
        this.setPayAmount(payAmount);
        this.setPayDateTime(payDateTime);
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStudID() {
        return walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayDateTime() {
        return payDateTime;
    }

    public void setPayDateTime(String payDateTime) {
        this.payDateTime = payDateTime;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = this.prodName;
    }
}
