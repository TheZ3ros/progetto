package com.example.progetto.bean;

import java.sql.Date;
import java.time.LocalDate;

public class TripCreationBean {
    private final int available;
    private final String city;
    private final Date data_and;
    private final Date data_rit;
    private final Float price;
    private final byte[] image;

    public TripCreationBean(String city,int available, Date data_and, Date data_rit, Float price, byte[] image){
        this.available=available;
        this.city=city;
        this.data_and=data_and;
        this.data_rit=data_rit;
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
    public Date getData_and(){
        return data_and;
    }
    public Date getData_rit(){
        return data_rit;
    }
    public float getPrice(){
        return price;
    }
}
