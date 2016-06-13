package com.jacobgreenland.weekendassignment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacobgreenland.weekendassignment2.adapter.BagAdapter;
import com.jacobgreenland.weekendassignment2.adapter.CategoryAdapter;
import com.jacobgreenland.weekendassignment2.adapter.ImageViewPagerAdapter;
import com.jacobgreenland.weekendassignment2.adapter.ProductAdapter;
import com.jacobgreenland.weekendassignment2.model.Details;
import com.jacobgreenland.weekendassignment2.model.Listing;
import com.jacobgreenland.weekendassignment2.model.ProductDetails;
import com.jacobgreenland.weekendassignment2.services.Services;
import com.jacobgreenland.weekendassignment2.utilities.Communicator;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Communicator{
    // Declaring Your View and Variables

    Toolbar toolbar;

    //Recycler Views for fragments
    RecyclerView mTab1Recycler;
    RecyclerView mTab2Recycler;
    RecyclerView mProductRecycler;
    RecyclerView mBagRecycler;
    //Adapters for Recycler Views
    CategoryAdapter mAdapter1;
    CategoryAdapter mAdapter2;
    ProductAdapter mProductAdapter;
    BagAdapter mBagAdapter;
    //Listings that are populated from the API
    public static List<Listing> tab1Listings;
    public static List<Listing> tab2Listings;
    public static List<Details> detailsList;
    public static ProductDetails productDetails;

    public static String chosenCategory;
    public static String chosenProduct;


    ViewPager detailsViewPager;
    TextView detailsName;
    TextView detailsPrice;
    TextView detailsDescription;
    Button addToBagButton;

    ImageView backButton;
    ImageView bagButton;
    public static ArrayList<ProductDetails> productsInBag = new ArrayList<>();

    public static OkHttpClient okHttpClient;
    public static long HTTP_CACHE_SIZE = 10 * 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        try {
            Services.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set OnClick for shopping
        bagButton = (ImageView) findViewById(R.id.bagButton);
        bagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "teste", Toast.LENGTH_LONG).show();

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                BagScreen bsFragment = new BagScreen();
                ft.addToBackStack(null);
                ft.replace(R.id.mainFrameLayout, bsFragment, bsFragment.toString());
                ft.commit();
            }
        });
        //Set OnClick for back button
        backButton = (ImageView) findViewById(R.id.home);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_LONG).show();
                //What to do on back clicked
                onBackPressed();
            }
        });
        //Set starting fragment to Tabs
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        TabChanger tabChange = new TabChanger();
        //ft.addToBackStack(null);
        ft.replace(R.id.mainFrameLayout, tabChange, "tabs");
        ft.commit();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();
        Log.d("test", " " + count);
        if (count == 0) {
            Log.d("test", "Restart the damn fragment");
            //restartFragment();
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void switchContent(int id, Fragment fragment, View view) {
        //Switch Fragments method
        Log.d("Test", "Fragment changed!");

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(id, fragment, fragment.toString());
            ft.addToBackStack(null);
            ft.commit();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home) {
            //NavUtils.navigateUpFromSameTask(MainActivity.this);
        }
        /*if(id)
        {

        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setRView(RecyclerView rv, int i)
    {
        //set recycler views from other fragments
        if(i == 1) {
            mTab1Recycler = rv;
            mTab1Recycler.setLayoutManager(new LinearLayoutManager(this));
            mTab1Recycler.setItemAnimator(new DefaultItemAnimator());
        }
        else if(i == 2)
        {
            mTab2Recycler = rv;
            mTab2Recycler.setLayoutManager(new LinearLayoutManager(this));
            mTab2Recycler.setItemAnimator(new DefaultItemAnimator());
        }
        else if(i == 3)
        {
            mProductRecycler = rv;
            mProductRecycler.setLayoutManager(new GridLayoutManager(this, 2));
            mProductRecycler.setItemAnimator(new DefaultItemAnimator());
        }
        else
        {
            mBagRecycler = rv;
            mBagRecycler.setLayoutManager(new LinearLayoutManager(this));
            mBagRecycler.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void afterViewsCreated(int i)
    {
        //set adapters for recyclerviews for each fragment
        if (i == 1) {
            mAdapter1 = new CategoryAdapter(tab1Listings, R.layout.category_row,  MainActivity.this);
            mTab1Recycler.setAdapter(mAdapter1);
        }
       else if(i == 2)
        {
            mAdapter2 = new CategoryAdapter(tab2Listings, R.layout.category_row,  MainActivity.this);
            mTab2Recycler.setAdapter(mAdapter2);
        }
        else if(i == 3)
        {
            mProductAdapter = new ProductAdapter(detailsList, R.layout.product_card, MainActivity.this);
            mProductRecycler.setAdapter(mProductAdapter);
        }
        else
        {
            if(productsInBag.size() != 0) {
                mBagAdapter = new BagAdapter(productsInBag, R.layout.bagscreen_layout, MainActivity.this);
                mBagRecycler.setAdapter(mBagAdapter);
            }
        }

    }
    @Override
    public void setUI(ViewPager vp, TextView name, TextView price, TextView description, Button add)
    {
        //Set UI elements for the details screen.
        detailsViewPager = vp;
        detailsName = name;
        detailsPrice = price;
        detailsDescription = description;
        addToBagButton = add;
        addToBagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addToBag();
                productsInBag.add(productDetails);
                //Toast.makeText(MainActivity.this, productsInBag.get(0).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void displayInformation()
    {
        //display detail information
        detailsName.setText(productDetails.getTitle());
        detailsPrice.setText(productDetails.getCurrentPrice());
        detailsDescription.setText(productDetails.getDescription());
        int s = productDetails.getProductImageUrls().size();
        Log.d("test", " " + s);
        ImageViewPagerAdapter iAdapter = new ImageViewPagerAdapter(MainActivity.this,productDetails.getProductImageUrls());
        detailsViewPager.setAdapter(iAdapter);
    }

    public void setUpHTTPClient()
    {
        File httpCacheDirectory = new File(getCacheDir(), "responses");

        Cache cache = null;
        try {
            cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        } catch (IOException e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }

        okHttpClient = new OkHttpClient();
        if (cache != null) {
            okHttpClient.setCache(cache);
        }

    }
}
