package com.example.progetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController {

    private Applicazione main;

    @FXML
    private void vai_a_Home(ActionEvent event){
        main.vai_a_Home();
    }

    public void setMain(Applicazione main){
        this.main = main;
    }
}
