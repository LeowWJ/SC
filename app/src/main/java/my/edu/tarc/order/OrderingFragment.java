/*package my.edu.tarc.order;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class OrderingFragment extends Fragment {
    public static final String UPLOAD_URL = "https://leowwj-wa15.000webhostapp.com/add_order.php";
    String walletID = OrderMainActivity.getwID();
    TextView textViewProductName, textViewProductDesc, textViewPrice, textViewTotal;
    EditText editTextAmount, editTextDiscount;
    double productPrice, total;
    Button buttonOrder, buttonApply;
    boolean ticketApplied;
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

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                orderDT = df.format(c.getTime());
                String orderQtyText = editTextAmount.getText().toString();
                int orderQuantity = 0;
                try{
                    orderQuantity = parseInt(orderQtyText);
                }
                catch(Exception e){
                    editTextAmount.setError("Only integer values are allowed.");
                }


                if (TextUtils.isEmpty(orderQtyText)) {
                    editTextAmount.setError("Field cannot be empty");
                }
                else if (orderQuantity <= 0){
                    editTextAmount.setError("Minimum 1 order shall be made to proceed.");
                }
                else {
                    total = orderQuantity * productPrice;
                    final AlertDialog.Builder confirmation = new AlertDialog.Builder(getActivity());
                    confirmation.setCancelable(false);
                    confirmation.setTitle("Amount To Be Paid");
                    confirmation.setPositiveButton("Pay",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    makeOrder();
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
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {

            String discountCode = editTextDiscount.getText().toString();
            @Override
            public void onClick(View view) {
                String discountCode = editTextDiscount.getText().toString();

                if (TextUtils.isEmpty(discountCode)) {
                    editTextAmount.setError("Field cannot be empty");
                }

                else {
                    ticketApplied = checkEligibilty(discountCode, walletID);
                }
            }
        });

        return v;
    }

    private void makeOrder() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {
            String ProdName = txtProdName.getText().toString();
            String ProdCat = txtCat.getText().toString();
            String ProdDesc = txtDesc.getText().toString();
            String ProdPrice = txtPrice.getText().toString();
            String ProdQuantity = txtQuantity.getText().toString();
            String SupplierName = txtSupplier.getText().toString();

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            protected void onPreExecute() {
                loading = ProgressDialog.show(OrderingFragment.this.getActivity(), "Making Order", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String ProdImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();


                data.put("ProdName", ProdName);
                data.put("ProdCat", ProdCat);
                data.put("ProdDesc", ProdDesc);
                data.put("ProdPrice", ProdPrice);
                data.put("ProdQuantity", ProdQuantity);
                data.put("ProdImage", ProdImage);
                data.put("MercName", Login.LOGGED_IN_USER);
                data.put("SupplierName",SupplierName);



                String result = rh.sendPostRequest(UPLOAD_URL, data);

                return result;
            }
        }
        UploadImage ui = new UploadImage();
        ui.execute(bitmap);

    }


}*/