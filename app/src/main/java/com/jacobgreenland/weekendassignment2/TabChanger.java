package com.jacobgreenland.weekendassignment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacobgreenland.weekendassignment2.TabClasses.SlidingTabLayout;
import com.jacobgreenland.weekendassignment2.TabClasses.ViewPagerAdapter;
import com.jacobgreenland.weekendassignment2.utilities.Communicator;

/**
 * Created by Jacob on 11/06/16.
 */
public class TabChanger extends Fragment
{
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Shop Women","Shop Men"};
    int Numboftabs =2;
    Communicator comm;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.tab_changer,container,false);

        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        getActivity().getFragmentManager();
        comm = (Communicator) getActivity();

        //set adapters and pagers for the Tab Screen
        adapter =  new ViewPagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) v.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


        Log.d("test", "Adapter should have been set by now!");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("test", "tabs have been resumed");

        //adapter =  new ViewPagerAdapter(getFragmentManager(),Titles,Numboftabs);
        //pager.setAdapter(adapter);

        //comm.restartFragment();
        //adapter.notifyDataSetChanged();
        //tabs.setViewPager(pager);
    }
}
