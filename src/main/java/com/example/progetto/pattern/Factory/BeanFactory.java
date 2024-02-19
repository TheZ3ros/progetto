package com.example.progetto.pattern.Factory;

public interface BeanFactory {
    BeanFactory createLoginBean();
    String getUsername();
    String getPassword();
    void setPassword(String password);
    void setUsername(String username);
}
