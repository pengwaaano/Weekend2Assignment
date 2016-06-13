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
 * Created by Jacob on 11/06/16.
 */
public class ProductList extends Fragment{

    Communicator comm;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.product_list,container,false);
        comm = (Communicator) getActivity();
        rv = (RecyclerView) v.findViewById(R.id.productList);

        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        comm.setRView(rv, 3);
        comm.afterViewsCreated(3);
        Log.d("test", "Adapter should have been set by now!");
    }
}
