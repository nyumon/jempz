package com.nyumon.jempol;

/**
 * Created by fajar on 05/04/16.
 */

public class ItemObject {

    private String name;
    private String time;
    private String judul;
    private int photo;

    public ItemObject(String name, String time, int photo, String judul) {
        this.name = name;
        this.time = time;
        this.photo = photo;
        this.judul = judul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setJudul(String judul){ this.judul = judul;}

    public String getJudul(){return judul;}
}
