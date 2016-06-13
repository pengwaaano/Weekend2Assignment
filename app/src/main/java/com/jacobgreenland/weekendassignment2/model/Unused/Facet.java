package com.jacobgreenland.weekendassignment2.model.Unused;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 11/06/16.
 */
public class Facet {

    @SerializedName("FacetValues")
    @Expose
    private List<FacetValues> facetValues = new ArrayList<FacetValues>();
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Sequence")
    @Expose
    private Integer sequence;

    /**
     *
     * @return
     * The facetValues
     */
    public List<FacetValues> getFacetValues() {
        return facetValues;
    }

    /**
     *
     * @param facetValues
     * The FacetValues
     */
    public void setFacetValues(List<FacetValues> facetValues) {
        this.facetValues = facetValues;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The Id
     */
    public void setId(String id) {
        this.id = id;
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
     * The sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *
     * @param sequence
     * The Sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
