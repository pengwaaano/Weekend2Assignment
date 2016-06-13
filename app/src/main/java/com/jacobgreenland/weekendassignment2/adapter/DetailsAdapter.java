package com.jacobgreenland.weekendassignment2.adapter;

/**
 * Created by Jacob on 09/06/16.
 */

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
import com.jacobgreenland.weekendassignment2.model.ProductDetails;
import com.jacobgreenland.weekendassignment2.utilities.ItemClickListener;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder>{

    private List<ProductDetails> details;
    private int rowLayout;
    private Context mContext;
    ProductList mFragment;

    public DetailsAdapter(List<ProductDetails> details, int rowLayout, Context context) {
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
        final ProductDetails detail= details.get(i);
        viewHolder.productName.setText(detail.getCurrentPrice());

        /*Picasso.with(mContext)
                .load(detail.getProductImageUrl().get(0))
                .into( viewHolder.productPhoto);*/
        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //MainActivity.chosenCategory = product.getCategoryId();

                //fragmentJump(view);

                Toast.makeText(mContext, "#" + position + " - " + detail.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    private void fragmentJump(View view) {
        mFragment = new ProductList();
        switchContent(R.id.mainFrameLayout, mFragment, view);
    }

    public void switchContent(int id, ProductList fragment, View view) {
        if (mContext == null) {
            Log.d("test", "this isn't good");
            return;
        }
        //if (mContext instanceof MainActivity) {
        Log.d("test", "this is better");
        MainActivity mainActivity = (MainActivity) mContext;
        ProductList frag = fragment;
        mainActivity.switchContent(id, frag, view);
        //}

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView productName;
        //public ImageView productPhoto;
        public TextView productPrice;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.pdName);
            //productPhoto = (ImageView) itemView.findViewById(R.id.productImage);
            productPrice = (TextView) itemView.findViewById(R.id.pdPrice);

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
