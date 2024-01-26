package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

   private Applicazione main;

   @FXML
   private void vai_a_Login(ActionEvent event){

      main.vai_a_Login();
   }

   public void setMain(Applicazione main){
      this.main = main;
   }
}