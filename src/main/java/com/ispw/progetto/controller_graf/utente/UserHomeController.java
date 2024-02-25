package com.ispw.progetto.controller_graf.utente;
import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.UserBean;
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
    public void vaiAHome(){

        main.vaiAHome();
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }
    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController=new MyTripController();
        myTripController.myTrip(currentUser,main);
    }
    public void info() throws IOException, SQLException {
        FXMLLoader infoLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/info_user.fxml"));
        Parent inforoot = infoLoader.load();
        Scene myTripScene = new Scene(inforoot);
        InfoUserController infoController = infoLoader.getController();
        infoController.setMain(main);
        infoController.setUser(currentUser);
        infoController.setInfo();
        Stage stage = main.getStage();
        stage.setTitle("I miei viaggi");
        stage.setScene(myTripScene);

    }
}
