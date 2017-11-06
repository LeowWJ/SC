package my.edu.tarc.order.Objects;

/**
 * Created by Leow on 11/4/2017.
 */

public class Product {
    private String ProdID;
    private String ProdName;
    private String ProdDesc;
    private double Price;
    private String ImageURL;
    private String MercName;



    public Product(){

    }




    public Product (String ProdID, String ProdName,String ProdDesc,Double Price,String ImageURL){
        this.setProdName(ProdName);
        this.setProdDesc(ProdDesc);
        this.setPrice(Price);
        this.setImageURL(ImageURL);
        this.setProdID(ProdID);
    }


    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public String getProdDesc() {
        return ProdDesc;
    }

    public void setProdDesc(String prodDesc) {
        ProdDesc = prodDesc;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getProdID() {
        return ProdID;
    }

    public void setProdID(String prodID) {
        ProdID = prodID;
    }

    public String getMercName() {
        return MercName;
    }

    public void setMercName(String mercName) {
        MercName = mercName;
    }


}
