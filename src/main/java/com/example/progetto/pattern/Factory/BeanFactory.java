package com.example.progetto.pattern.Factory;

public interface BeanFactory {
    String getUsername();
    String getPassword();
    void setPassword(String password);
    void setUsername(String username);
}
