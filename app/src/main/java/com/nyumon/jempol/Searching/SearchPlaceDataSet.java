package com.nyumon.jempol.Searching;

/**
 * Created by riskteria on 10/04/16.
 */
public class SearchPlaceDataSet {

    String placename;
    int totalposts;

    public SearchPlaceDataSet(String placename, int totalposts) {
        this.placename  = placename;
        this.totalposts = totalposts;
    }

    public String getPlacename() { return placename; }
    public int getTotalposts() { return totalposts; }

}
