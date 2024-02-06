package com.example.progetto.bean;

import java.sql.Date;

public class TripBean {
    private final int available;
    private final String city;
    private final Date data_and;
    private final Date data_rit;
    private final Float price;
    private final byte[] image;
    private int id;

    public TripBean(String city, int available, Date data_and, Date data_rit, Float price, byte[] image){
        this.available=available;
        this.city=city;
        this.data_and=data_and;
        this.data_rit=data_rit;
        this.price=price;
        this.image=image;
    }
    public TripBean(int places, String city, Date dataAnd, Date dataRit, Float price, byte[] image, int id){
        this.available=places;
        this.city=city;
        this.data_and=dataAnd;
        this.data_rit=dataRit;
        this.price=price;
        this.image=image;
        this.id=id;
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
        return data_and;
    }
    public Date getDataRit(){
        return data_rit;
    }
    public float getPrice(){
        return price;
    }
    public int getId(){
        return id;
    }
}
