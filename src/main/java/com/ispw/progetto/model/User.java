package com.ispw.progetto.model;

import com.ispw.progetto.pattern.factory.EntityFactory;

public class User implements EntityFactory {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;


@Override
    public void setUsername(String user){
            username =user;
    }
    @Override
    public void setPassword(String pass){
        password=pass;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername(){
        return username;
    }

    public String getCognome() {
        return cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
