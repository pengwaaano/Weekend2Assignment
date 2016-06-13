package com.jacobgreenland.weekendassignment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacobgreenland.weekendassignment2.utilities.Communicator;

/**
 * Created by Jacob on 12/06/16.
 */
public class BagScreen extends Fragment{

    View v;
    RecyclerView rv;
    Communicator comm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.bag_screen,container,false);

        comm = (Communicator) getActivity();
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.bs_Recycler);
        comm.setRView(rv,4);
        comm.afterViewsCreated(4);

        Log.d("test", "Adapter should have been set by now!");
    }

}
