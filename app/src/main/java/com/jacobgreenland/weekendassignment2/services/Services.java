package com.jacobgreenland.weekendassignment2.services;

import com.jacobgreenland.weekendassignment2.observables.IItemAPI;
import com.jacobgreenland.weekendassignment2.utilities.Constants;

import retrofit.RestAdapter;

/**
 * Created by Jacob on 09/06/16.
 */
public class Services {

    // -----------------------------------------------------------------------------------
    public static IItemAPI _createItemAPI() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build().create(IItemAPI.class);
    }
}