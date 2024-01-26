package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void vai_a_Home(ActionEvent event){

        main.vai_a_Home();
    }
    @FXML
    private void view_trip(ActionEvent event) throws IOException {

        FXMLLoader ViewTripLoader = new FXMLLoader(Applicazione.class.getResource("view_trip.fxml"));
        Parent ViewTripRoot = ViewTripLoader.load();
        Scene viewTripScene = new Scene(ViewTripRoot);
        ViewTripController viewtrip = ViewTripLoader.getController();
        viewtrip.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(viewTripScene);
        viewtrip.setButtonText();
        stage.setTitle("Ricerca");

    }

}
