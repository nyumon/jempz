package com.nyumon.jempol.Langganan;


public class LanggananDataset {

    private String username, displayname;
    private int posts, subscribers;
    private Boolean subscribe;

    public LanggananDataset(String username, String displayname, int posts, int subscribers,Boolean subscribe) {
        this.username    = username;
        this.displayname = displayname;
        this.posts       = posts;
        this.subscribers = subscribers;
        this.subscribe   = subscribe;

    }

    public String getUsername()     { return username; }
    public String getDisplayname()  { return displayname; }
    public int getPosts()           { return posts; }
    public int getSubscribers()     { return subscribers; }
    public Boolean getSubscribe()    {return subscribe;}

}
