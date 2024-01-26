package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

   private Applicazione main;
   private Scene loginScene;
   private Stage stage;

   @FXML
   private void vai_a_Login(ActionEvent event) throws IOException {

      FXMLLoader loginLoader = new FXMLLoader(Applicazione.class.getResource("login.fxml"));
      Parent loginRoot = loginLoader.load();
      loginScene = new Scene(loginRoot);
      LoginController loginController = loginLoader.getController();
      loginController.setMain(main);
      stage=main.getStage();
      stage.setScene(loginScene);
      stage.setTitle("Accedi");
   }

   public void setMain(Applicazione main){

      this.main = main;
   }
}