package my.edu.tarc.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import my.edu.tarc.order.Objects.Order;
import my.edu.tarc.order.Objects.Product;

public class OrderMainActivity extends AppCompatActivity{

    private static String canteenName, stallName, walletID, productID, productName, productDesc,
            orderStatus, orderDateTime;
    private static int orderID, orderAmount, loyaltyPoint;
    private static double walletBal, productPrice, payAmount;
    public static List<Product> listMenu = null;
    public static List<Order> listOrder = null;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    CanteenFragment canteenFragment = new CanteenFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,canteenFragment).commit();
                    break;
                case R.id.navigation_orderHistory:
                    OrderHistoryFragment orderHistoryFragment = new OrderHistoryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,orderHistoryFragment).commit();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "ERROR: NO EXTRAS FOUND!", Toast.LENGTH_SHORT).show();
            finish();
        }
        walletID = extras.getString("walletID");
        walletBal = extras.getDouble("balance", 0);
        loyaltyPoint = extras.getInt("loyaltyPoint",0);
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static String getCanteen() {
        return canteenName;
    }

    public static void setCanteen(String cName) {
        canteenName = cName;
    }

    public static String getStall() {
        return stallName;
    }

    public static void setStall(String nameOfStall) {
        stallName = nameOfStall;
    }

    public static String getwID() {
        return walletID;
    }

    public static String getProdID() {
        return productID;
    }

    public static void setProdID(String prodID) {
        productID = prodID;
    }

    public static String getProdName() {
        return productName;
    }

    public static void setProdName(String prodName) {
        productName = prodName;
    }

    public static String getProdDesc() {
        return productDesc;
    }

    public static void setProdDesc(String prodDesc) {
        productDesc = prodDesc;
    }

    public static double getProdPrice() {
        return productPrice;
    }

    public static void setProdPrice(double prodPrice) {
        productPrice = prodPrice;
    }

    public static double getOrderTotal() {
        return payAmount;
    }

    public static void setOrderTotal(double total) {
        payAmount = total;
    }

    public static int getOrderID() {
        return orderID;
    }

    public static void setOrderID(int oID) {
        orderID = oID;
    }

    public static String getOrderStatus() {
        return orderStatus;
    }

    public static void setOrderStatus(String orderStat) {
        orderStatus = orderStat;
    }

    public static String getOrderDateTime() {
        return orderDateTime;
    }

    public static void setOrderDateTime(String orderDT) {
        orderDateTime = orderDT;
    }

    public static int getOrderAmount() {
        return orderAmount;
    }

    public static void setOrderAmount(int orderQuantity) {
        orderAmount = orderQuantity;
    }

}