package my.edu.tarc.order.Objects;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import my.edu.tarc.order.R;

/**
 * Created by Leow on 11/4/2017.
 */

public class MenuAdapter extends ArrayAdapter<Product> {
    public MenuAdapter(Activity context, int resource, List<Product> list) {
        super(context, resource, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Product grid = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = inflater.inflate(R.layout.menu_layout, parent, false);

        TextView textViewProductName, textViewPrice;
        ImageView imageViewProduct;

        textViewProductName = gridView.findViewById(R.id.textViewProductName);
        textViewPrice = gridView.findViewById(R.id.textViewPrice);
        imageViewProduct = gridView.findViewById(R.id.imageViewProduct);

        if (grid != null) {
            textViewProductName.setText(textViewProductName.getText() + ":  " + grid.getProdName());
        }
        textViewPrice.setText( textViewPrice.getText() + ":  RM " + grid.getPrice());
        getImage(grid.getImageURL(), imageViewProduct);

        return gridView;
    }

    private void getImage(String urlToImage, final ImageView ivImage) {
        class GetImage extends AsyncTask<String, Void, Bitmap> {
            ProgressDialog loading;

            @Override
            protected Bitmap doInBackground(String... params) {
                URL url = null;
                Bitmap image = null;

                String urlToImage = params[0];
                try {
                    url = new URL(urlToImage);
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return image;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Downloading Image...", "Please wait...", true, true);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                loading.dismiss();
                ivImage.setImageBitmap(bitmap);
            }
        }
        GetImage gi = new GetImage();
        gi.execute(urlToImage);
    }
}
