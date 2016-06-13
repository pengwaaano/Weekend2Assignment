package com.jacobgreenland.weekendassignment2.model;

        import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
        import com.jacobgreenland.weekendassignment2.model.Unused.Facet;

        import java.util.ArrayList;
import java.util.List;

public class ProductsList {

    @SerializedName("AlsoSearched")
    @Expose
    private List<Object> alsoSearched = new ArrayList<Object>();
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Facets")
    @Expose
    private List<Facet> facets = new ArrayList<Facet>();
    @SerializedName("ItemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("Listings")
    @Expose
    private List<Details> details = new ArrayList<Details>();
    @SerializedName("RedirectUrl")
    @Expose
    private String redirectUrl;
    @SerializedName("SortType")
    @Expose
    private String sortType;

    /**
     *
     * @return
     * The alsoSearched
     */
    public List<Object> getAlsoSearched() {
        return alsoSearched;
    }

    /**
     *
     * @param alsoSearched
     * The AlsoSearched
     */
    public void setAlsoSearched(List<Object> alsoSearched) {
        this.alsoSearched = alsoSearched;
    }

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
     * The facets
     */
    public List<Facet> getFacets() {
        return facets;
    }

    /**
     *
     * @param facets
     * The Facets
     */
    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    /**
     *
     * @return
     * The itemCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     *
     * @param itemCount
     * The ItemCount
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     *
     * @return
     * The listings
     */
    public List<Details> getDetails() {
        return details;
    }

    /**
     *
     * @param listings
     * The Listings
     */
    public void setDetails(List<Details> listings) {
        this.details = listings;
    }

    /**
     *
     * @return
     * The redirectUrl
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     *
     * @param redirectUrl
     * The RedirectUrl
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     *
     * @return
     * The sortType
     */
    public String getSortType() {
        return sortType;
    }

    /**
     *
     * @param sortType
     * The SortType
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}


