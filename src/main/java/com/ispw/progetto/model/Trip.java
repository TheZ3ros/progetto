package com.ispw.progetto.model;

import java.sql.Date;

public class Trip {
    private final int available;
    private final String city;
    private final Date dataAnd;
    private final Date dataRit;
    private final Float price;
    private final byte[] image;
    private boolean stato;
    private int id;
    public Trip(int n, String city, Date dataAnd, Date dataRit,Float price,byte[] image, boolean stato){
        this.city=city;
        this.image=image;
        this.available=n;
        this.price=price;
        this.dataAnd=dataAnd;
        this.dataRit=dataRit;
        this.stato=stato;
    }
    public Trip(int n, String city, Date dataAnd, Date dataRit,Float price,byte[] image, int id){
        this.city=city;
        this.image=image;
        this.available=n;
        this.price=price;
        this.dataAnd=dataAnd;
        this.dataRit=dataRit;
        this.id=id;
    }

    public Trip(int n, String city, Date dataAnd, Date dataRit,Float price,byte[] image){
        this.city=city;
        this.image=image;
        this.available=n;
        this.price=price;
        this.dataAnd=dataAnd;
        this.dataRit=dataRit;
    }

    public int getAvailable() {
        return available;
    }


    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public byte[] getImage() {
        return image;
    }

    public Date getDataAnd() {
        return dataAnd;
    }

    public Date getDataRit() {
        return dataRit;
    }

    public float getPrice() {
        return price;
    }

    public boolean isStato() {
        return stato;
    }
}
//Controller applicativo controlla il bean, se tutto va bene crea l’entity e la passa al dao.