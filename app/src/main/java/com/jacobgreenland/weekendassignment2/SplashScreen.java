package com.jacobgreenland.weekendassignment2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.jacobgreenland.weekendassignment2.model.Category;
import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.services.Services;

import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jacob on 11/06/16.
 */
public class SplashScreen extends AppCompatActivity
{

    private IItemAPI _api;
    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private ProgressDialog pDialog;
    MySpinnerDialog myInstance;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);


        pb = (ProgressBar) findViewById(R.id.splashProgress);

        _api = Services._createItemAPI();
        pattern();

    }
    public void pattern(){
    // load in Women Category
        _subscriptions.add(_api.getWomensCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5000, TimeUnit.MILLISECONDS)
                .retry()
                .distinct()
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");
                        //Once loaded, load mens category
                        pattern2();
                        hidePDialog();
                    }
                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i("Retrofit", "onCompleted");
                    }
                    @Override
                    public void onNext(Category category) {
                        Log.i("Retrofit", "onNext");
                        MainActivity.tab1Listings = category.getListing();
                        hidePDialog();

                    }
                }));

    }
    public void pattern2()
    {
        //load in Mens Category
        _subscriptions.add(_api.getMensCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(5000, TimeUnit.MILLISECONDS)
                .retry()
                .distinct()
                .subscribe(new Observer<Category>() {
                    @Override
                    public void onCompleted() {
                        Log.i("Retrofit", "onCompleted");
                        //myInstance.dismiss();
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        //pb.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Throwable e)
                    {
                        Log.i("Retrofit", "onCompleted");
                    }
                    @Override
                    public void onNext(Category category) {
                        Log.i("Retrofit", "onNext");

                        MainActivity.tab2Listings = category.getListing();
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

    public static class MySpinnerDialog extends DialogFragment {

        private ProgressDialog pDialog;
        public MySpinnerDialog() {
            // use empty constructors. If something is needed use onCreate's
        }

        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {

            pDialog = new ProgressDialog(getActivity());
            this.setStyle(STYLE_NO_TITLE, getTheme()); // You can use styles or inflate a view
            pDialog.setMessage("Spinning.."); // set your messages if not inflated from XML

            pDialog.setCancelable(false);

            return pDialog;
        }
    }
}
