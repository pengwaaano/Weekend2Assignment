package com.jacobgreenland.weekendassignment2.model.Unused;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Variant {

    @SerializedName("BasePrice")
    @Expose
    private Double basePrice;
    @SerializedName("Brand")
    @Expose
    private Object brand;
    @SerializedName("Colour")
    @Expose
    private String colour;
    @SerializedName("CurrentPrice")
    @Expose
    private String currentPrice;
    @SerializedName("InStock")
    @Expose
    private Boolean inStock;
    @SerializedName("IsInSet")
    @Expose
    private Boolean isInSet;
    @SerializedName("PreviousPrice")
    @Expose
    private String previousPrice;
    @SerializedName("PriceType")
    @Expose
    private String priceType;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductImageUrls")
    @Expose
    private List<String> productImageUrls = new ArrayList<String>();
    @SerializedName("RRP")
    @Expose
    private String rRP;
    @SerializedName("Size")
    @Expose
    private String size;
    @SerializedName("Sku")
    @Expose
    private Object sku;
    @SerializedName("Title")
    @Expose
    private Object title;

    /**
     *
     * @return
     * The basePrice
     */
    public Double getBasePrice() {
        return basePrice;
    }

    /**
     *
     * @param basePrice
     * The BasePrice
     */
    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     *
     * @return
     * The brand
     */
    public Object getBrand() {
        return brand;
    }

    /**
     *
     * @param brand
     * The Brand
     */
    public void setBrand(Object brand) {
        this.brand = brand;
    }

    /**
     *
     * @return
     * The colour
     */
    public String getColour() {
        return colour;
    }

    /**
     *
     * @param colour
     * The Colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     *
     * @return
     * The currentPrice
     */
    public String getCurrentPrice() {
        return currentPrice;
    }

    /**
     *
     * @param currentPrice
     * The CurrentPrice
     */
    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     *
     * @return
     * The inStock
     */
    public Boolean getInStock() {
        return inStock;
    }

    /**
     *
     * @param inStock
     * The InStock
     */
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    /**
     *
     * @return
     * The isInSet
     */
    public Boolean getIsInSet() {
        return isInSet;
    }

    /**
     *
     * @param isInSet
     * The IsInSet
     */
    public void setIsInSet(Boolean isInSet) {
        this.isInSet = isInSet;
    }

    /**
     *
     * @return
     * The previousPrice
     */
    public String getPreviousPrice() {
        return previousPrice;
    }

    /**
     *
     * @param previousPrice
     * The PreviousPrice
     */
    public void setPreviousPrice(String previousPrice) {
        this.previousPrice = previousPrice;
    }

    /**
     *
     * @return
     * The priceType
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     *
     * @param priceType
     * The PriceType
     */
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    /**
     *
     * @return
     * The productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     * The ProductId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     *
     * @return
     * The productImageUrls
     */
    public List<String> getProductImageUrls() {
        return productImageUrls;
    }

    /**
     *
     * @param productImageUrls
     * The ProductImageUrls
     */
    public void setProductImageUrls(List<String> productImageUrls) {
        this.productImageUrls = productImageUrls;
    }

    /**
     *
     * @return
     * The rRP
     */
    public String getRRP() {
        return rRP;
    }

    /**
     *
     * @param rRP
     * The RRP
     */
    public void setRRP(String rRP) {
        this.rRP = rRP;
    }

    /**
     *
     * @return
     * The size
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The Size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The sku
     */
    public Object getSku() {
        return sku;
    }

    /**
     *
     * @param sku
     * The Sku
     */
    public void setSku(Object sku) {
        this.sku = sku;
    }

    /**
     *
     * @return
     * The title
     */
    public Object getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The Title
     */
    public void setTitle(Object title) {
        this.title = title;
    }

}
