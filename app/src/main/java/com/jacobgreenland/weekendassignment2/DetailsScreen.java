package com.jacobgreenland.weekendassignment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jacobgreenland.weekendassignment2.utilities.Communicator;

/**
 * Created by Jacob on 11/06/16.
 */
public class DetailsScreen extends Fragment {

    Communicator comm;
    View v;
    ViewPager vp;
    TextView title;
    TextView price;
    TextView description;
    Button addToBag;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.product_details,container,false);

        comm = (Communicator) getActivity();
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        vp = (ViewPager) v.findViewById(R.id.detailsPager);
        title = (TextView) v.findViewById(R.id.pdName);
        price = (TextView) v.findViewById(R.id.pdPrice);
        description = (TextView) v.findViewById(R.id.pd_Description);
        addToBag = (Button) v.findViewById(R.id.pd_AddToBag);
        comm.setUI(vp,title,price, description, addToBag);
        comm.displayInformation();

        Log.d("test", "Adapter should have been set by now!");
    }
}
