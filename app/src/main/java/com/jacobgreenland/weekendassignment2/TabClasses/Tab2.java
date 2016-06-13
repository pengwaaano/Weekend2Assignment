package com.jacobgreenland.weekendassignment2.TabClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacobgreenland.weekendassignment2.R;
import com.jacobgreenland.weekendassignment2.utilities.Communicator;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab2 extends Fragment {

    Communicator comm;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);
        comm = (Communicator) getActivity();
        rv = (RecyclerView) v.findViewById(R.id.tab2List);
        //comm.setRView(rv, 2);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        comm.setRView(rv, 2);
        comm.afterViewsCreated(2);
        //comm.afterViewsCreated();
        //comm = (Communicator) getActivity();
    }
}