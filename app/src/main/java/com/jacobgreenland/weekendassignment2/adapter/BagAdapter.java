package com.jacobgreenland.weekendassignment2.adapter;

/**
 * Created by Jacob on 09/06/16.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jacobgreenland.weekendassignment2.ProductList;
import com.jacobgreenland.weekendassignment2.R;
import com.jacobgreenland.weekendassignment2.model.ProductDetails;
import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.utilities.ItemClickListener;

import java.util.ArrayList;

import rx.subscriptions.CompositeSubscription;


public class BagAdapter extends RecyclerView.Adapter<BagAdapter.ViewHolder>{

    private ArrayList<ProductDetails> listings;
    private int rowLayout;
    private Context mContext;
    ProductList mFragment;

    private IItemAPI _api;
    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private ProgressDialog pDialog;


    public BagAdapter(ArrayList<ProductDetails> products, int rowLayout, Context context) {
        this.listings = products;
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
        final ProductDetails productDetails = listings.get(i);
        viewHolder.listingName.setText(productDetails.getTitle());
        viewHolder.listingPrice.setText(productDetails.getCurrentPrice());
        /*Picasso.with(mContext)
                .load(Constants.BASE_URL + "/photos/" + cake.getPhoto())
                .into( viewHolder.cakeImage);*/
        /*viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                /*MainActivity.chosenCategory = listing.getCategoryId();

                _api = Services._createItemAPI();
                pattern(view);


                //Toast.makeText(mContext, "#" + position + " - " + productDetails.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listings == null ? 0 : listings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView listingName;
        public TextView listingPrice;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            listingName = (TextView) itemView.findViewById(R.id.bs_name);
            listingPrice = (TextView) itemView.findViewById(R.id.bs_Price);

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
