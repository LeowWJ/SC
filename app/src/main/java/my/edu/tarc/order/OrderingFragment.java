package my.edu.tarc.order;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import static java.lang.Integer.parseInt;


public class OrderingFragment extends Fragment {
    public static final String UPLOAD_URL = "https://leowwj-wa15.000webhostapp.com/add_order.php";
    String walletID = OrderMainActivity.getwID();
    TextView textViewProductName, textViewProductDesc, textViewPrice, textViewTotal;
    EditText editTextAmount, editTextDiscount;
    int orderQty;
    double productPrice, total;
    Button buttonOrder, buttonApply;
    static boolean ticketApplied;
    String orderDT;
    ProgressDialog progressDialog;



    public OrderingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ordering, container, false);
        textViewProductName = v.findViewById(R.id.textViewProductName);
        textViewProductDesc = v.findViewById(R.id.textViewProductDesc);
        textViewPrice = v.findViewById(R.id.textViewPrice);
        textViewTotal = v.findViewById(R.id.textViewTotal);
        textViewProductName.setText(OrderMainActivity.getProdName());
        textViewProductDesc.setText(OrderMainActivity.getProdDesc());
        textViewPrice.setText(OrderMainActivity.getProdPrice() + "");
        productPrice = OrderMainActivity.getProdPrice();


        String prodID = OrderMainActivity.getProdID();
        total = 0;

        buttonOrder = v.findViewById(R.id.buttonOrder);
        buttonApply = v.findViewById(R.id.buttonApplyCode);

        editTextAmount.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                try{
                    orderQty = Integer.parseInt(editTextAmount.getText().toString());
                    if (TextUtils.isEmpty(editTextAmount.getText().toString())) {
                        editTextAmount.setError("Field cannot be empty");
                    }
                    else if (orderQty <= 0){
                        editTextAmount.setError("Minimum 1 order shall be made to proceed.");
                    }
                    else{
                        total = orderQty * productPrice;
                        textViewTotal.setText(textViewTotal.getText().toString() + " " + total);
                    }
                }
                catch(Exception e){
                    editTextAmount.setError("Only integer values are allowed.");
                }
                return true;
            }
        });

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    final AlertDialog.Builder confirmation = new AlertDialog.Builder(getActivity());
                    confirmation.setCancelable(false);
                    confirmation.setTitle("Amount To Be Paid");
                    confirmation.setMessage("RM " + total + " will be duducted from your wallet.");
                    confirmation.setPositiveButton("Pay",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //makeOrder();
                                    OrderMainActivity.listOrder = null;
                                }
                            });
                    confirmation.setNegativeButton("Back to Ordering",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    confirmation.show();
                }
            }
        );



        buttonApply.setOnClickListener(new View.OnClickListener() {

            String discountCode = editTextDiscount.getText().toString();
            @Override
            public void onClick(View view) {
                String discountCode = editTextDiscount.getText().toString();

                if (TextUtils.isEmpty(discountCode)) {
                    editTextAmount.setError("Field cannot be empty");
                }

                else {
                    checkEligibilty(getActivity(), "https://leowwj-wa15.000webhostapp.com/smart%20canteen%20system/getOrderHistory.php", discountCode, walletID);
                    if (ticketApplied == true && editTextAmount.getText().toString().matches("^10.*")){
                        total -= 10;
                        textViewTotal.setText(R.string.total + " " + total);
                    }
                    else if (ticketApplied == true && editTextAmount.getText().toString().matches("^5.*")){
                        total -= 5;
                        textViewTotal.setText(R.string.total + " " + total);
                    }
                }
            }
        });

        return v;
    }

    public void checkEligibilty(Context context, String url, final String disCode, final String wID){
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);
        Boolean result = false;
        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Boolean res;
                            JSONObject jsonObject;
                            try {
                                String err = "";
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                String message = jsonObject.getString("message");
                                if (success == 0) {
                                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    OrderingFragment.ticketApplied = true;

                                } else if (success == 1) {
                                        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        OrderingFragment.ticketApplied = false;
                                }
                                else {
                                    Toast.makeText(getActivity().getApplicationContext(), "err", Toast.LENGTH_SHORT).show();
                                    OrderingFragment.ticketApplied = false;
                                }
                                //show error
                                if (err.length() > 0) {
                                    Toast.makeText(getActivity().getApplicationContext(), err, Toast.LENGTH_LONG).show();
                                    OrderingFragment.ticketApplied = false;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("WalletID", wID);
                    params.put("DiscountCode",disCode);
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