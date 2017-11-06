package my.edu.tarc.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import my.edu.tarc.order.Objects.DataCommunication;
import my.edu.tarc.order.Objects.Order;

public class MainActivity extends AppCompatActivity implements DataCommunication{

    private String canteenName;
    private String stallName;
    private String StudID = null;
    private int ProdID = 0;
    public static Order OrderDetail;

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

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public String getCanteen() {
        return canteenName;
    }

    @Override
    public void setCanteen(String canteenName) {
        this.canteenName = canteenName;
    }

    @Override
    public String getStall() {
        return stallName;
    }

    @Override
    public void setStall(String stallName) {
        this.stallName = stallName;
    }

    @Override
    public String getStudID() {
        return StudID;
    }

    @Override
    public void setStudID(String studID) {
        this.StudID = studID;
    }

    @Override
    public int getProdID() {
        return ProdID;
    }

    @Override
    public void setProdID(int prodID) {
        this.ProdID = prodID;
    }

    @Override
    public void setOrderDesc(int orderID, String productName, double Total, String orderStatus, String orderDateTime) {
        OrderDetail.setOrderID(orderID);
        OrderDetail.setProdName(productName);
        OrderDetail.setPayAmount(Total);
        OrderDetail.setOrderStatus(orderStatus);
        OrderDetail.setOrderDateTime(orderDateTime);
    }




}
