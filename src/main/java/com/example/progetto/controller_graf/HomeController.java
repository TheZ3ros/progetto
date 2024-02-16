package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

   private Applicazione main;

   @FXML
   private void vaiALogin() throws IOException {

      FXMLLoader loginLoader = new FXMLLoader(Applicazione.class.getResource("view1/login.fxml"));
      Parent loginRoot = loginLoader.load();
      Scene loginScene = new Scene(loginRoot);
      LoginController loginController = loginLoader.getController();
      loginController.setMain(main);
      Stage stage = main.getStage();
      stage.setScene(loginScene);
      stage.setTitle("Accedi");
   }

   public void setMain(Applicazione main){

      this.main = main;
   }
}