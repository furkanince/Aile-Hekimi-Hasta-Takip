package com.example.furkan.ik2;

public class Users {
    public String ad,departman,email,image,isebaslamatarihi,telefon;

    public Users(String ad, String departman, String email, String image, String isebaslamatarihi, String telefon) {

        this.ad = ad;
        this.departman = departman;
        this.email = email;
        this.image = image;
        this.isebaslamatarihi = isebaslamatarihi;
        this.telefon = telefon;
    }
    public Users(){

    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsebaslamatarihi() {
        return isebaslamatarihi;
    }

    public void setIsebaslamatarihi(String isebaslamatarihi) {
        this.isebaslamatarihi = isebaslamatarihi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
