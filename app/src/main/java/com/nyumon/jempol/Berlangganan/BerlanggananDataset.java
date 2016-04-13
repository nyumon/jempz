package com.nyumon.jempol.Berlangganan;


public class BerlanggananDataset {

    private String username, displayname;
    private int posts, subscribers;
    private Boolean subscribe;

    public BerlanggananDataset(String username, String displayname, int posts, int subscribers ) {
        this.username    = username;
        this.displayname = displayname;
        this.posts       = posts;
        this.subscribers = subscribers;

    }

    public String getUsername()     { return username; }
    public String getDisplayname()  { return displayname; }
    public int getPosts()           { return posts; }
    public int getSubscribers()     { return subscribers; }


}
