package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class UserHomeController implements StageAware {
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
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
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
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/utente/info_user.fxml", this);
//        FXMLLoader infoLoader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/info_user.fxml"));
//        Parent infoRoot = infoLoader.load();
//        Scene infoScene = new Scene(infoRoot);
//
//        InfoUserController infoController = infoLoader.getController();
//        infoController.setStage(stage);
//        infoController.setUser(currentUser);
//        infoController.setInfo();
//
//        stage.setScene(infoScene);
//        stage.setTitle("I miei viaggi");
    }
}
