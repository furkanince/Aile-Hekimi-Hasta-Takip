package com.example.furkan.ik2;

public class Calisan {

    private String ad;
    private String soyad;
    private String departman;

    public Calisan() {
    }

    public Calisan(String ad, String soyad, String departman) {
        if(ad.trim().equals(""))
        {
            ad="Ad girilmedi";
        }
        if(soyad.trim().equals(""))
        {
            soyad="SoyAd girilmedi";
        }
        if(departman.trim().equals(""))
        {
            departman="Departman girilmedi";
        }

        this.ad = ad;
        this.soyad = soyad;
        this.departman = departman;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }
}

