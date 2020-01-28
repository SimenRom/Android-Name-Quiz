package com.example.thenamequiz;

import android.graphics.Bitmap;

public class Profil {
    private String navn;
    private Bitmap bilde;

    public Profil (String navn, Bitmap bilde){
        this.navn = navn;
        this.bilde = bilde;
    }
    public String getNavn(){return navn;}
    public Bitmap getBilde(){return bilde;}

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBilde(Bitmap bilde) {
        this.bilde = bilde;
    }
}
