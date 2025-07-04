package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_graf.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UserHomeController {
    @FXML
    private Button user;

    private Stage stage;
    private UserBean currentUser;

    public void setUser(UserBean utente) {
        currentUser = utente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setButtonText() {
        user.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/home.fxml"));
        Parent homeRoot = homeLoader.load();
        Scene homeScene = new Scene(homeRoot);
        HomeController homeController = homeLoader.getController();
        homeController.setStage(stage);  // importante: passare lo stage!
        stage.setScene(homeScene);
        stage.setTitle("Home");
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page = new ViewTripController();
        page.viewTrip(stage, currentUser);
    }

    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController = new MyTripController();
        myTripController.myTrip(currentUser, stage);
    }

    public void info() throws IOException, SQLException {
        FXMLLoader infoLoader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/info_user.fxml"));
        Parent infoRoot = infoLoader.load();
        Scene infoScene = new Scene(infoRoot);

        InfoUserController infoController = infoLoader.getController();
        infoController.setStage(stage);
        infoController.setUser(currentUser);
        infoController.setInfo();

        stage.setScene(infoScene);
        stage.setTitle("I miei viaggi");
    }
}
