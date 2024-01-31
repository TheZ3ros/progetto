package com.example.progetto.bean;

import java.util.Date;

public class TripBean {
    private final int places;
    private final String city;
    private final Date data_and;
    private final Date data_rit;
    private final Float price;
    private final String image;
    private final int id;

    public TripBean(int places, String city, Date data_and, Date data_rit, Float price, String image, int id){
        this.places=places;
        this.city=city;
        this.data_and=data_and;
        this.data_rit=data_rit;
        this.price=price;
        this.image=image;
        this.id=id;
    }
    public int getPlaces(){
        return places;
    }
    public String getCity(){
        return city;
    }
    public String image(){
        return image;
    }
    public Date getData_and(){
        return data_and;
    }
    public Date getData_rit(){
        return data_rit;
    }
    public float getPrice(){
        return price;
    }
    public int getId(){
        return id;
    }

}
