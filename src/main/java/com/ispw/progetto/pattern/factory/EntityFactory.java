package com.ispw.progetto.pattern.factory;

public interface EntityFactory {
    String getUsername();
    String getPassword();
    void setPassword(String password);
    void setUsername(String username);
}
