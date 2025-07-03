package com.ispw.progetto.controller_graf;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

   private Stage stage;

   // Setter per lo stage
   public void setStage(Stage stage) {
      this.stage = stage;
   }

   @FXML
   private void vaiALogin() throws IOException {
      FXMLLoader loginLoader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/login.fxml"));
      Parent loginRoot = loginLoader.load();
      Scene loginScene = new Scene(loginRoot);
      LoginController loginController = loginLoader.getController();
      loginController.setStage(stage);  // passa lo stage anche al LoginController
      stage.setScene(loginScene);
      stage.setTitle("Accedi");
   }

   @FXML
   public void registrati() throws IOException {
      FXMLLoader regLoader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/registrazione.fxml"));
      Parent regRoot = regLoader.load();
      Scene regScene = new Scene(regRoot);
      RegistrazioneController regController = regLoader.getController();
      regController.setStage(stage);  // passa lo stage anche al RegistrazioneController
      stage.setScene(regScene);
      stage.setTitle("Registrazione");
   }
}
