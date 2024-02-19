package com.example.progetto.controller_graf.utente;
import com.example.progetto.Applicazione;
import com.example.progetto.pattern.Factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class UserHomeController {
    @FXML
    private Button user;
    private Applicazione main;
    private BeanFactory currentUser;

    public void setUser(BeanFactory utente){

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
}
