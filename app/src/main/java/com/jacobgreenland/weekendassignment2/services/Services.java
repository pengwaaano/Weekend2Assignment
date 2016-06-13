package com.jacobgreenland.weekendassignment2.services;

import android.util.Log;

import com.jacobgreenland.weekendassignment2.MainActivity;
import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.utilities.Constants;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Jacob on 09/06/16.
 */
public class Services {

    // -----------------------------------------------------------------------------------
    public static IItemAPI _createItemAPI() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setClient(new OkClient(MainActivity.okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build().create(IItemAPI.class);
    }

    public static void run() throws Exception {
        Request request = new Request.Builder()
                .url(Constants.BASE_URL)
                .build();

        Response response1 = MainActivity.okHttpClient.newCall(request).execute();
        if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

        String response1Body = response1.body().string();
        //System.out.println("Response 1 response:          " + response1);
        Log.d("Test", "Response 1 response:           " + response1);
        System.out.println("Response 1 cache response:    " + response1.cacheResponse());
        System.out.println("Response 1 network response:  " + response1.networkResponse());

        Response response2 = MainActivity.okHttpClient.newCall(request).execute();
        if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);

        String response2Body = response2.body().string();
        System.out.println("Response 2 response:          " + response2);
        System.out.println("Response 2 cache response:    " + response2.cacheResponse());
        System.out.println("Response 2 network response:  " + response2.networkResponse());

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }
}