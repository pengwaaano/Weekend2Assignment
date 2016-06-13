package com.jacobgreenland.weekendassignment2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Listing")
    @Expose
    private List<Listing> listing = new ArrayList<Listing>();

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The listing
     */
    public List<Listing> getListing() {
        return listing;
    }

    /**
     *
     * @param listing
     * The Listing
     */
    public void setListing(List<Listing> listing) {
        this.listing = listing;
    }

}