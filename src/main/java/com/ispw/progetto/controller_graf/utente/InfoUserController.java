package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class InfoUserController {

    @FXML
    private Button user;

    private Stage stage;
    private UserBean currentUser;

    @FXML
    private Text tnome;
    @FXML
    private Text tcognom;
    @FXML
    private Text temail;
    @FXML
    private Text tusername;

    // Setter per lo stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(UserBean utente) {
        currentUser = utente;
    }

    public void setButtonText() {
        user.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() {
        stage.setTitle("Home");
        stage.setScene(new Scene(new javafx.scene.Group())); // puoi caricare la scena vera della home se disponibile
    }

    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController = new MyTripController();
        myTripController.myTrip(currentUser, stage);
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page = new ViewTripController();
        page.viewTrip(stage, currentUser);
    }

    public void setInfo() throws IOException, SQLException {
        RegLoginControllerApp regLoginControllerApp = new RegLoginControllerApp(currentUser);
        SignUpUserBean userr = regLoginControllerApp.info();

        tnome.setText(userr.getNome());
        tcognom.setText(userr.getCognome());
        temail.setText(userr.getUsername());
        tusername.setText(userr.getEmail());
    }
}
