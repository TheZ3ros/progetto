package com.example.progetto.entity;
import java.sql.Date;

public class Trip {
    private int available;
    private  String city;
    private  Date data_and;
    private  Date data_rit;
    private  Float price;
    private  String image;
    private int id;


    public int getAvailable() {
        return available;
    }
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getImage() {
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

    public void setAvailable(int posti) {
        this.available =posti;
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
//Controller applicativo controlla il bean, se tutto va bene crea l’entity e la passa al dao.