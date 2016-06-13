package com.jacobgreenland.weekendassignment2.adapter;

/**
 * Created by Jacob on 09/06/16.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jacobgreenland.weekendassignment2.MainActivity;
import com.jacobgreenland.weekendassignment2.ProductList;
import com.jacobgreenland.weekendassignment2.R;
import com.jacobgreenland.weekendassignment2.model.Listing;
import com.jacobgreenland.weekendassignment2.model.ProductsList;
import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.services.Services;
import com.jacobgreenland.weekendassignment2.utilities.ItemClickListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private List<Listing> listings;
    private int rowLayout;
    private Context mContext;
    ProductList mFragment;

    private IItemAPI _api;
    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private ProgressDialog pDialog;


    public CategoryAdapter(List<Listing> categories, int rowLayout, Context context) {
        this.listings = categories;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Listing listing = listings.get(i);
        viewHolder.listingName.setText(listing.getName());
        /*Picasso.with(mContext)
                .load(Constants.BASE_URL + "/photos/" + cake.getPhoto())
                .into( viewHolder.cakeImage);*/
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                    //Chosen category to pass to the API
                    MainActivity.chosenCategory = listing.getCategoryId();
                    //Create API

                    _api = Services._createItemAPI();
                    pattern(view);

                    Toast.makeText(mContext, "#" + position + " - " + listing.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void pattern(final View view)
    {
        //Log.d("test", MainActivity.chosenCategory);
        _subscriptions.add(_api.getProducts(MainActivity.chosenCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5000, TimeUnit.MILLISECONDS)
                .retry()
                .distinct()
                .subscribe(new Observer<ProductsList>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");
                        //jump fragment
                        fragmentJump(view);
                        hidePDialog();
                    }
                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i("Retrofit", "onCompleted");
                    }
                    @Override
                    public void onNext(ProductsList productsList) {
                        Log.i("Retrofit", "onNext");
                        MainActivity.detailsList = productsList.getDetails();
                        hidePDialog();

                    }
                }));
    }
    private void hidePDialog()
    {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public int getItemCount() {
        return listings == null ? 0 : listings.size();
    }

    private void fragmentJump(View view) {
        //call switch content to proceed with changing fragment
        mFragment = new ProductList();
        switchContent(R.id.mainFrameLayout, mFragment, view);
    }

    public void switchContent(int id, ProductList fragment, View view) {
        if (mContext == null) {
            Log.d("test", "this isn't good");
            return;
        }
        // jump to main activity to switch fragment
            Log.d("test", "this is better");
            MainActivity mainActivity = (MainActivity) mContext;
            ProductList frag = fragment;
            mainActivity.switchContent(id, frag, view);
        //}

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView listingName;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            listingName = (TextView) itemView.findViewById(R.id.categoryName);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition());
            return true;
        }
    }
}
