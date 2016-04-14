package com.nyumon.jempol.Timeline;

/**
 * Created by fajar on 05/04/16.
 */

public class TimelineDataSet {

    private String name;
    private String time;
    private String judul;
    private String kategori;
    private int photo;
    private int photoAkun;

    public TimelineDataSet(int photoAkun, String name, String time, int photo, String kategori, String judul) {
        this.photoAkun = photoAkun;
        this.name      = name;
        this.time      = time;
        this.photo     = photo;
        this.kategori  = kategori;
        this.judul     = judul;
    }

    public void setPhotoAkun(int photoAkun) { this.photoAkun = photoAkun; }
    public void setName(String name)        { this.name      = name;      }
    public void setPhoto(int photo)         { this.photo     = photo;     }
    public void setJudul(String judul)      { this.judul     = judul;     }
    public void setKategori(String kategori){ this.kategori  = kategori;  }
    public void setTime(String time)        { this.time      = time;      }

    public int getPhotoAkun()   { return photoAkun; }
    public String getName()     { return name;      }
    public String getTime()     { return time;      }
    public int getPhoto()       { return photo;     }
    public String getJudul()    { return judul;     }
    public String getKategori() { return kategori;  }

}
