package com.example.progetto.bean;

import java.sql.Date;

public class TripBean {
    private final int available;
    private final String city;
    private final Date dataAnd;
    private final Date dataRit;
    private final Float price;
    private final byte[] image;
    private int id;
    private boolean stato;

    public TripBean(String city, int available, Date dataAnd, Date dataRit, Float price, byte[] image,boolean stato){
        this.available=available;
        this.city=city;
        this.dataAnd = dataAnd;
        this.dataRit = dataRit;
        this.price=price;
        this.image=image;
        this.stato=stato;
    }
    public TripBean(int places, String city, Date dataAnd, Date dataRit, Float price, byte[] image, int id){
        this.available=places;
        this.city=city;
        this.dataAnd =dataAnd;
        this.dataRit =dataRit;
        this.price=price;
        this.image=image;
        this.id=id;
    }
    public TripBean(String city, int available, Date dataAnd, Date dataRit, Float price, byte[] image){
        this.available=available;
        this.city=city;
        this.dataAnd = dataAnd;
        this.dataRit = dataRit;
        this.price=price;
        this.image=image;
    }
    public int getAvailable(){
        return available;
    }
    public String getCity(){
        return city;
    }
    public byte[] getImage(){
        return image;
    }
    public Date getDataAnd(){
        return dataAnd;
    }
    public Date getDataRit(){
        return dataRit;
    }
    public float getPrice(){
        return price;
    }
    public int getId(){
        return id;
    }

    public boolean isStato() {
        return stato;
    }
}
