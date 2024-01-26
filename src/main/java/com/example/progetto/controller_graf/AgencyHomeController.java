package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AgencyHomeController {
    @FXML
    private Button agency;
    private Applicazione main;
    private AgencyBean currentUser;

    public void setUser(AgencyBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){

        this.main = main;
    }
    @FXML
    private void vai_a_Home(){

        main.vai_a_Home();
    }
    /*
    @FXML
    private void view_trip() throws IOException {

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
*/
}
