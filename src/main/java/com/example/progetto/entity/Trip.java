package com.example.progetto.entity;
import java.util.Date;

public class Trip {
    private int places;
    private  String city;
    private  Date data_and;
    private  Date data_rit;
    private  Float price;
    private  String image;
    private int id;


    public int getPlaces() {
        return places;
    }
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String image() {
        return image;
    }

    public Date getData_and() {
        return data_and;
    }

    public Date getData_rit() {
        return data_rit;
    }

    public float getPrice() {
        return price;
    }

    public void setPlaces(int posti) {
        this.places=posti;
    }
    public void setCity(String citta) {
        this.city=citta;
    }
    public void setPrice(float prezzo) {
        this.price=prezzo;
    }
    public void setData_rit(Date data) {
        this.data_rit=data;
    }
    public void setData_and(Date data) {
        this.data_and=data;
    }
    public void setImage(String image) {
        this.image=image;
    }
    public void setId(int id){
        this.id=id;
    }

}
