package my.edu.tarc.order;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import my.edu.tarc.order.Objects.DataCommunication;



public class CanteenFragment extends Fragment {

    Button canteen1, canteen2, canteen3, canteen4;
    public static boolean allowRefresh;
    DataCommunication canteenIdentifier;

    public CanteenFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_canteen, container, false);
        allowRefresh = false;

        canteen1 = (Button)v.findViewById(R.id.buttonCanteen1);
        canteen2 = (Button)v.findViewById(R.id.buttonCanteen2);
        canteen3 = (Button)v.findViewById(R.id.buttonCanteen3);
        canteen4 = (Button)v.findViewById(R.id.buttonCanteen4);


        canteen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canteenIdentifier.setCanteen("Red Bricks Cafeteria");
                StallFragment nextFrag= new StallFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        // buttonCanteen2, buttonCanteen3 and buttonCanteen4 are disabled
        // until function is added for them.
        canteen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature will be available on next update.",
                        Toast.LENGTH_LONG);
            }
        });

        canteen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature will be available on next update.",
                        Toast.LENGTH_LONG);
            }
        });

        canteen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "This feature will be available on next update.",
                        Toast.LENGTH_LONG);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            canteenIdentifier = (DataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

}
