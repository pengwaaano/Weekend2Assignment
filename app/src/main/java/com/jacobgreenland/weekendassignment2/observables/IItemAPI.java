package com.jacobgreenland.weekendassignment2.observables;

import com.jacobgreenland.weekendassignment2.model.Category;
import com.jacobgreenland.weekendassignment2.model.ProductDetails;
import com.jacobgreenland.weekendassignment2.model.ProductsList;
import com.jacobgreenland.weekendassignment2.utilities.Constants;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Jacob on 10/06/16.
 */
public interface IItemAPI {
    @Headers("Cache-Control: max-age=640000")
    @GET(Constants.WOMEN_URL)
    Observable<Category> getWomensCategory();
    @GET(Constants.MEN_URL)
    Observable<Category> getMensCategory();
    @GET(Constants.CATEGORY_URL)
    Observable<ProductsList> getProducts(@Query("catid") String category);
    @GET(Constants.ITEM_URL)
    Observable<ProductDetails> getProductDetails(@Query("catid") String details);
}
