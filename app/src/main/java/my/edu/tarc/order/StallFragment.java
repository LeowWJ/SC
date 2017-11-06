package my.edu.tarc.order;

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


public class StallFragment extends Fragment {

    Button buttonStall1, buttonStall2, buttonStall3, buttonStall4, buttonStall5, buttonStall6, buttonStall7, buttonStall8, buttonStall9;
    public static boolean allowRefresh;
    DataCommunication stallIdentifier;
    String canteenChoice = stallIdentifier.getCanteen();

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

        buttonStall1 = (Button)v.findViewById(R.id.buttonStall1);
        buttonStall2 = (Button)v.findViewById(R.id.buttonStall2);
        buttonStall3 = (Button)v.findViewById(R.id.buttonStall3);
        buttonStall4 = (Button)v.findViewById(R.id.buttonStall4);
        buttonStall5 = (Button)v.findViewById(R.id.buttonStall5);
        buttonStall6 = (Button)v.findViewById(R.id.buttonStall6);
        buttonStall7 = (Button)v.findViewById(R.id.buttonStall7);
        buttonStall8 = (Button)v.findViewById(R.id.buttonStall8);
        buttonStall9 = (Button)v.findViewById(R.id.buttonStall9);

        switch (canteenChoice){
            case "Red Bricks Cafeteria":
                buttonStall1.setText("@string/convenienceStore");
                buttonStall2.setText("@string/mixedRice");
                buttonStall3.setText("@string/masakanMalaysia");
                buttonStall4.setText("@string/noodles");
                buttonStall5.setText("@string/fresh");
                buttonStall6.setText("@string/indoDeli");
                buttonStall7.setText("@string/vegetarianFood");
                buttonStall8.setText("@string/monsterCafe");
                buttonStall9.setVisibility(View.INVISIBLE);
                buttonStall9.setClickable(false);

                buttonStall1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stallIdentifier.setStall("Convenience Store");
                        MenuFragment nextFrag= new MenuFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, nextFrag,"findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                });

                buttonStall2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                buttonStall8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "This feature will be available on next update.",
                                Toast.LENGTH_LONG);
                    }
                });

                break;

                //add in cases for other canteen and set the buttons' text
                // to provide service for the stalls at other canteen.
        }

        return v;
    }


    @Override
    public void onAttach(Context context) {
       super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            stallIdentifier = (DataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
