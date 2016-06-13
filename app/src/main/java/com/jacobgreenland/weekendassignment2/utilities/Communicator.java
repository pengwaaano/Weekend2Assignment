package com.jacobgreenland.weekendassignment2.utilities;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jacob on 11/06/16.
 */
public interface Communicator {
    public void setRView(RecyclerView rv, int i);
    public void afterViewsCreated(int i);
    public void setUI(ViewPager vp, TextView name, TextView price, TextView description, Button add);
    public void displayInformation();
}
