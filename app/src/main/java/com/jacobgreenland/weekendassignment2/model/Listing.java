package com.jacobgreenland.weekendassignment2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacob on 10/06/16.
 */
public class Listing {

    @SerializedName("CategoryId")
    @Expose
    private String categoryId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProductCount")
    @Expose
    private Integer productCount;

    /**
     *
     * @return
     * The categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     *
     * @param categoryId
     * The CategoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The productCount
     */
    public Integer getProductCount() {
        return productCount;
    }

    /**
     *
     * @param productCount
     * The ProductCount
     */
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

}