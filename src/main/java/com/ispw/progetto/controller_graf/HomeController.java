package com.ispw.progetto.controller_graf;

import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController implements StageAware {

   private Stage stage;

   // Setter per lo stage
   public void setStage(Stage stage) {
      this.stage = stage;
   }

   @FXML
   private void vaiALogin() throws IOException {
      SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/login.fxml", this);
   }

   @FXML
   public void registrati() throws IOException {
      SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/registrazione.fxml", this);
   }
}
