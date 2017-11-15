/*package my.edu.tarc.order;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.Integer.parseInt;


public class OrderingFragment extends Fragment {
    public static final String UPLOAD_URL = "https://leowwj-wa15.000webhostapp.com/add_order.php";
    TextView textViewProductName, textViewProductDesc, textViewPrice, textViewTotal;
    EditText editTextAmount, editTextDiscount;
    int amount;
    double productPrice, total;
    Button buttonOrder, buttonApply;
    boolean ticketApplied;


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
        buttonOrder = v.findViewById(R.id.buttonOrder);
        buttonApply = v.findViewById(R.id.buttonApplyCode);
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => "+c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String orderDT = df.format(c.getTime());

                String walletID = OrderMainActivity.getwID();
                String prodID = OrderMainActivity.getProdID();
                String orderDateTime = orderDT;
                String orderQtyText = editTextAmount.getText().toString();
                int orderQuantity = parseInt(orderQtyText);
                double payAmount = orderQuantity * OrderMainActivity.getProdPrice();
                String discountCode = editTextDiscount.getText().toString();

                if (TextUtils.isEmpty(orderQtyText)) {
                    editTextAmount.setError("Field cannot be empty");
                }

                else {
                    makeOrder();
                    MenuFragment.allowRefresh = true;
                    OrderMainActivity.listOrder = null;
                }
            }
        });

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discountCode = editTextDiscount.getText().toString();

                if (TextUtils.isEmpty(discountCode)) {
                    editTextAmount.setError("Field cannot be empty");
                }

                else {
                    ticketApplied = checkEligibilty(discountCode);
                    MenuFragment.allowRefresh = true;
                    OrderMainActivity.listOrder = null;
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

    private boolean checkEligibilty(String couponCode){
        boolean result;

        return result;
    }
}
*/