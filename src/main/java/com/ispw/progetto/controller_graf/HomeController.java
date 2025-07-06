package com.ispw.progetto.controller_graf;

import com.ispw.progetto.utils.AppContext;
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
   private AppContext appContext; // 🔹 nuovo campo

   public void setStage(Stage stage) {
      this.stage = stage;
   }

   public void setAppContext(AppContext appContext) {  // 🔹 nuovo metodo setter
      this.appContext = appContext;
   }

   @FXML
   private void vaiALogin() throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/login.fxml"));
      Parent root = loader.load();
      LoginController controller = loader.getController();
      controller.setStage(stage);
      controller.setAppContext(appContext); // 🔹 propaga il contesto
      stage.setScene(new Scene(root));
      stage.setTitle("Login");
   }

   @FXML
   public void registrati() throws IOException {
      // Simile al login, se necessario
      SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/registrazione.fxml", this);
   }
}

