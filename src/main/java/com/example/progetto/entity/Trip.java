package com.example.progetto.entity;
import com.example.progetto.DAO.TripDAO;

import java.util.Date;

public class Trip {
    private final int places;
    private final String city;
    private final Date data_and;
    private final Date data_rit;
    private final Float price;
    private final String image;

public Trip(int places, String city, Date data_and, Date data_rit, Float price, String image){
    this.places=places;
    this.city=city;
    this.data_and=data_and;
    this.data_rit=data_rit;
    this.price=price;
    this.image=image;
}
public int getId(){
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
}
