package com.example.progetto.model;

public class User {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;


    public void setUser(String user){
            username =user;
    }
    public void setPassword(String pass){
        password=pass;
    }
    public String getPassword(){
        return password;
    }
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

    public void setUsername(String username) {
        this.username = username;
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
