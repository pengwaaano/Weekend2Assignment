package com.jacobgreenland.weekendassignment2.TabClasses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacobgreenland.weekendassignment2.R;
import com.jacobgreenland.weekendassignment2.utilities.Communicator;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

    Communicator comm;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        comm = (Communicator) getActivity();
        rv = (RecyclerView) v.findViewById(R.id.tab1List);

        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        comm.setRView(rv, 1);
        comm.afterViewsCreated(1);
        Log.d("test", "Adapter should have been set by now!");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("test", "RESUME LIST");
        comm.setRView(rv, 1);
        comm.afterViewsCreated(1);
        //adapter.notifyDataSetChanged();
        //pager.setAdapter(adapter);
        //tabs.setViewPager(pager);

    }
}
