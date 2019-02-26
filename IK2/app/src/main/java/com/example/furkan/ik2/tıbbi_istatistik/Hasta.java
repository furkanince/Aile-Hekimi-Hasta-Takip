package com.example.furkan.ik2.tıbbi_istatistik;

public class Hasta {
    public String ad,meslek,ilac,ilac_bas_trh,image;

    public Hasta() {
    }

    public Hasta(String ad, String meslek, String ilac, String ilac_bas_trh, String image) {
        this.ad = ad;
        this.meslek = meslek;
        this.ilac = ilac;
        this.ilac_bas_trh = ilac_bas_trh;
        this.image = image;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getIlac() {
        return ilac;
    }

    public void setIlac(String ilac) {
        this.ilac = ilac;
    }

    public String getIlac_bas_trh() {
        return ilac_bas_trh;
    }

    public void setIlac_bas_trh(String ilac_bas_trh) {
        this.ilac_bas_trh = ilac_bas_trh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
