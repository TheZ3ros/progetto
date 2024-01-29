package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
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
    private Applicazione main;
    private UserBean currentUser;

    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){

        this.main = main;
    }
    @FXML
    private void vai_a_Home(){

        main.vai_a_Home();
    }
    @FXML
    private void view_trip() throws IOException, SQLException {

        FXMLLoader ViewTripLoader = new FXMLLoader(Applicazione.class.getResource("view_trip.fxml"));
        Parent ViewTripRoot = ViewTripLoader.load();
        Scene viewTripScene = new Scene(ViewTripRoot);
        ViewTripController viewtrip = ViewTripLoader.getController();
        viewtrip.setMain(main);
        viewtrip.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(viewTripScene);
        viewtrip.setButtonText();
        viewtrip.charge();
        stage.setTitle("Ricerca");

    }

}
