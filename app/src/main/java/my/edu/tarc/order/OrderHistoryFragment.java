package my.edu.tarc.order;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import my.edu.tarc.order.Objects.Order;
import my.edu.tarc.order.Objects.OrderHistoryAdapter;


public class OrderHistoryFragment extends Fragment {
    public static boolean allowRefresh;
    public static final String TAG = "com.example.user.myApp";

    ListView listViewOrderHistory = null;
    int OrderID;
    ProgressDialog progressDialog;
    RequestQueue queue;
    public static List<Order> listOrderHistory = null;
    public String walletID = OrderMainActivity.getwID();

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_order_history, container, false);
        allowRefresh = false;
        listViewOrderHistory = (ListView)v.findViewById(R.id.listViewOrderHistory);
        if (listOrderHistory == null) {
            listOrderHistory = new ArrayList<>();

            String type = "retrieveOrderHistory";
            BackgroundWorker backgroundWorker = new BackgroundWorker(v.getContext());
            backgroundWorker.execute(type,  walletID);


        } else {
            loadListing();
        }

        listViewOrderHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Order chosenOrder = (Order) parent.getItemAtPosition(position);
                int chosenOrderID = chosenOrder.getOrderID();
                String orderDateTime = chosenOrder.getOrderDateTime();
                String chosenProdName = chosenOrder.getProdName();
                int chosenOrderAmount = chosenOrder.getOrderQuantity();
                double chosenOrderTotal = chosenOrder.getPayAmount();
                String chosenOrderStatus = chosenOrder.getOrderStatus();
                OrderMainActivity.setOrderID(chosenOrderID);
                OrderMainActivity.setProdName(chosenProdName);
                OrderMainActivity.setOrderDateTime(orderDateTime);
                OrderMainActivity.setOrderAmount(chosenOrderAmount);
                OrderMainActivity.setOrderTotal(chosenOrderTotal);
                OrderMainActivity.setOrderStatus(chosenOrderStatus);
                OrderDetailFragment nextFrag= new OrderDetailFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

    private void loadListing() {
        final OrderHistoryAdapter adapter = new OrderHistoryAdapter(getActivity(), R.layout.fragment_menu, listOrderHistory);
        listViewOrderHistory.setAdapter(adapter);

    }


    private class BackgroundWorker extends AsyncTask<String, Void, String> {

        Context context;
        AlertDialog alertDialog;


        public BackgroundWorker(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String borrowURL = "https://leowwj-wa15.000webhostapp.com/smart%20canteen%20system/getOrderHistory.php";

            // if the type of the task = retrieveBorrowHistory
            if (type == "retrieveOrderHistory") {
                String StudID = params[1];

                try {

                    //establish httpUrlConnection with POST method
                    URL url = new URL(borrowURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    //set output stream
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("StudID", "UTF-8") + "=" + URLEncoder.encode(StudID, "UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    // read the data
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            if (!progressDialog.isShowing()) ;
            progressDialog.setMessage("Retrieving Order History");
            progressDialog.show();


        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONArray jsonArray = new JSONArray(result);

                try {
                    listOrderHistory.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject courseResponse= (JSONObject) jsonArray.get(i);



                        int orderID = courseResponse.getInt("OrderID");
                        String prodName = courseResponse.getString("ProdName");
                        String orderDateTime = courseResponse.getString("OrderDateTime");
                        int orderQuantity = courseResponse.getInt("OrderQuantity");
                        String orderStatus = courseResponse.getString("OrderStatus");
                        double payAmount = courseResponse.getDouble("PayAmount");
                        String payDateTime = courseResponse.getString("PayDateTime");

                        if (orderStatus == "Pending" || orderStatus == "Cancelled"){
                            Order history = new Order(orderID, prodName, orderQuantity, payAmount,
                                    orderStatus, orderDateTime);
                            listOrderHistory.add(history);
                        }
                        else{
                            Order history = new Order(orderID, prodName, orderQuantity, payAmount,
                                    orderStatus, orderDateTime, payDateTime);
                            listOrderHistory.add(history);
                        }

                    }

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                    loadListing();



                } catch (Exception e) {
                    Toast.makeText(getView().getContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getView().getContext(), "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

    public void onDestroyView() {
        super.onDestroyView();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }

}
