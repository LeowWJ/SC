package my.edu.tarc.order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class OrderDetailFragment extends Fragment {

    Button buttonCancelOrder, buttonPay;
    TextView textViewOrderID, textViewProductName, textViewPayment, textViewPaymentStatus, textViewOrderDateTime;
    Integer orderID = MainActivity.OrderDetail.getOrderID();
    String oID = orderID.toString();
    final String TAG = "OrderID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_order_detail, container, false);
        int orderID = MainActivity.OrderDetail.getOrderID();
        String prodName = MainActivity.OrderDetail.getProdName();
        double total = MainActivity.OrderDetail.getPayAmount();
        String status = MainActivity.OrderDetail.getOrderStatus();
        String orderDateTime = MainActivity.OrderDetail.getOrderDateTime();
        textViewOrderID.setText(orderID);
        textViewProductName.setText(prodName);
        textViewPayment.setText(total + " ");
        textViewOrderDateTime.setText(orderDateTime);
        textViewPaymentStatus.setText(status);
        if (status == "Completed" || status == "Cancelled"){
            buttonCancelOrder.setVisibility(View.INVISIBLE);
            buttonPay.setVisibility(View.INVISIBLE);
        }

        buttonCancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final View view = (LayoutInflater.from(v.getContext()).inflate(R.layout.order_history_layout, null));
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
                    alertBuilder.setView(view);
                    alertBuilder.setTitle("Delete this order?");

                    alertBuilder.setCancelable(true)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        updateStatus(view.getContext(), "https://leowwj-wa15.000webhostapp.com/smart%20canteen%20system/update_name.php");
                                }
                            });

                    Dialog dialog = alertBuilder.create();
                    dialog.show();
                    MenuFragment.allowRefresh = true;

                    OrderHistoryFragment.listOrderHistory = null;

                }
            });

        buttonCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add this, your class to the bracket, not sure the syntax correct or not
                Intent intent = new Intent();
                intent.putExtra(TAG,oID);
                startActivity(intent);
            }
        });

        return v;
    }

    public void updateStatus(Context context, String url) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        //Send data
        try {

            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //response =string returned by server to the client
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                String message = jsonObject.getString("message");
                                if (success == 0) {

                                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                } else {

                                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getView().getContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("OrderID", oID);
                    params.put("OrderStatus", "Cancelled");

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
