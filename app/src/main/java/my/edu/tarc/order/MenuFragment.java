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
import android.widget.GridView;
import android.widget.Toast;

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

import my.edu.tarc.order.Objects.DataCommunication;
import my.edu.tarc.order.Objects.MenuAdapter;
import my.edu.tarc.order.Objects.Product;

import com.android.volley.RequestQueue;

public class MenuFragment extends Fragment {

    DataCommunication stallIdentifier;
    public static boolean allowRefresh;
    public static final String TAG = "my.edu.tarc.order";
    public String MercName = stallIdentifier.getStall();
    public static List<Product> listMenu = null;

    GridView gridViewMenu;
    String walletID;
    DataCommunication walletIdentifier;
    ProgressDialog progressDialog;
    RequestQueue queue;

    public MenuFragment() {
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
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        allowRefresh = false;
        gridViewMenu = (GridView)v.findViewById(R.id.gridViewMenu);

        progressDialog = new ProgressDialog(v.getContext());

        if(MenuFragment.listMenu == null){
            listMenu = new ArrayList<>();
            String type = "retrieveMenu";
            BackgroundWorker backgroundWorker = new BackgroundWorker(v.getContext());
            backgroundWorker.execute(type,  MercName);
        }
        else {
            loadListing();
        }
        gridViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                MainActivity.OrderDetail.setOrderID(position);

            }
        });
            return v;
    }

    private void loadListing() {
        final MenuAdapter adapter = new MenuAdapter(getActivity(), R.layout.fragment_menu,listMenu);
        gridViewMenu.setAdapter(adapter);

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
            String borrowURL = "https://leowwj-wa15.000webhostapp.com/smart%20canteen%20system/getMenuItem.php";

            // if the type of the task = retrieveBorrowHistory
            if (type == "retrieveMenu") {
                String MercName = params[1];

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
                    String post_data = URLEncoder.encode("MercName", "UTF-8") + "=" + URLEncoder.encode(MercName, "UTF-8");

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
            progressDialog.setMessage("Retrieving Menu List");
            progressDialog.show();


        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONArray jsonArray = new JSONArray(result);

                try {
                    listMenu.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject courseResponse= (JSONObject) jsonArray.get(i);



                        String ProdID = courseResponse.getString("ProdID");
                        String ProdName = courseResponse.getString("ProdName");
                        String ProdDesc = courseResponse.getString("ProdDesc");
                        double ProdPrice = Double.parseDouble(courseResponse.getString("ProdPrice"));
                        String ImageURL = courseResponse.getString("url");

                        Product menuItem = new Product(ProdID, ProdName, ProdDesc, ProdPrice, ImageURL);
                        listMenu.add(menuItem);

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


