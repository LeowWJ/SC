package my.edu.tarc.order.Objects;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import my.edu.tarc.order.R;

/**
 * Created by Leow on 11/4/2017.
 */

public class OrderHistoryAdapter extends ArrayAdapter<Order> {

    public OrderHistoryAdapter(Activity context, int resource, List<Order> list){
        super(context, resource, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Order list = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView = inflater.inflate(R.layout.order_history_layout, parent, false);

        TextView textViewOrderDate, textViewAmount, textViewTotal, textViewStatus;

        textViewOrderDate = (TextView) listView.findViewById(R.id.textViewOrderDate);
        textViewAmount = (TextView) listView.findViewById(R.id.textViewAmount);
        textViewTotal = (TextView) listView.findViewById(R.id.textViewTotal);
        textViewStatus = (TextView) listView.findViewById(R.id.textViewStatus);

        textViewOrderDate.setText(textViewOrderDate.getText() + " " + list.getOrderDateTime());
        textViewAmount.setText(textViewAmount.getText() + " " + list.getOrderQuantity());
        textViewTotal.setText(textViewTotal.getText() + " RM " + list.getPayAmount());
        textViewStatus.setText(textViewStatus.getText() + " " + list.getOrderStatus());

        return listView;
    }


}
