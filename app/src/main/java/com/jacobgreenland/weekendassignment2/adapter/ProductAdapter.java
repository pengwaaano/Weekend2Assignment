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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jacobgreenland.weekendassignment2.DetailsScreen;
import com.jacobgreenland.weekendassignment2.MainActivity;
import com.jacobgreenland.weekendassignment2.R;
import com.jacobgreenland.weekendassignment2.model.Details;
import com.jacobgreenland.weekendassignment2.model.ProductDetails;
import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.services.Services;
import com.jacobgreenland.weekendassignment2.utilities.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Details> details;
    private int rowLayout;
    private Context mContext;
    DetailsScreen mFragment;

    private IItemAPI _api;
    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private ProgressDialog pDialog;

    public ProductAdapter(List<Details> details, int rowLayout, Context context) {
        this.details = details;
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
        final Details detail= details.get(i);
        viewHolder.productName.setText(detail.getCurrentPrice());

        Picasso.with(mContext)
                .load(detail.getProductImageUrl().get(0))
                .into( viewHolder.productPhoto);
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                    //MainActivity.chosenCategory = product.getCategoryId();
                    MainActivity.chosenProduct = detail.getProductId().toString();
                    _api = Services._createItemAPI();
                    //fragmentJump(view);
                    pattern(view);
                    Toast.makeText(mContext, "#" + position + " - " + detail.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    public void pattern(final View view)
    {
        //Log.d("test", MainActivity.chosenCategory);
        _subscriptions.add(_api.getProductDetails(MainActivity.chosenProduct)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5000, TimeUnit.MILLISECONDS)
                .retry()
                .distinct()
                .subscribe(new Observer<ProductDetails>() {
                    @Override
                    public void onCompleted()
                    {
                        Log.i("Retrofit", "onCompleted");
                        fragmentJump(view);
                        hidePDialog();
                    }
                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i("Retrofit", "Error");
                    }
                    @Override
                    public void onNext(ProductDetails detailsList)
                    {
                        Log.i("Retrofit", "onNext");
                        MainActivity.productDetails = detailsList;
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

    private void fragmentJump(View view) {
        mFragment = new DetailsScreen();
        switchContent(R.id.mainFrameLayout, mFragment, view);
    }

    public void switchContent(int id, DetailsScreen fragment, View view) {
        if (mContext == null) {
            Log.d("test", "this isn't good");
            return;
        }
        //if (mContext instanceof MainActivity) {
        Log.d("test", "this is better");
        MainActivity mainActivity = (MainActivity) mContext;
        DetailsScreen frag = fragment;
        mainActivity.switchContent(id, frag, view);
        //}

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView productName;
        public ImageView productPhoto;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPhoto = (ImageView) itemView.findViewById(R.id.productImage);

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
