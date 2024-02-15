package com.example.progetto.entity;
import java.sql.Date;

public class Trip {
    private int available;
    private  String city;
    private  Date dataAnd;
    private  Date dataRit;
    private  Float price;
    private  byte[] image;
    private int id;
    public Trip(int n, String city, Date dataAnd, Date dataRit,Float price,byte[] image){
        this.city=city;
        this.image=image;
        this.available=n;
        this.price=price;
        this.dataAnd=dataAnd;
        this.dataRit=dataRit;
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

    public void setAvailable(int posti) {
        this.available =posti;
    }
    public void setCity(String citta) {
        this.city=citta;
    }
    public void setPrice(float prezzo) {
        this.price=prezzo;
    }
    public void setDataRit(Date data) {
        this.dataRit =data;
    }
    public void setDataAnd(Date data) {
        this.dataAnd =data;
    }
    public void setImage(byte[] image) {
        this.image=image;
    }
    public void setId(int id){
        this.id=id;
    }

}
//Controller applicativo controlla il bean, se tutto va bene crea l’entity e la passa al dao.