package com.nyumon.jempol.Searching;

/**
 * Created by riskteria on 04/04/16.
 */
public class SearchEventDataSet {

    private String place, username, catagory;
    private int jempol_total;

    public SearchEventDataSet(String place, String username, String catagory, int jempol_total) {
        this.place        = place;
        this.username     = username;
        this.catagory     = catagory;
        this.jempol_total = jempol_total;
    }

    public String getPlace()     { return place; }
    public String getUsername()  { return username; }
    public String getCatagory()  { return catagory; }
    public int getJempol_total() { return jempol_total; }

}
