package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.SignUpUserBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.pattern.factory.EntityFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class InfoUserController {
    private Button user;
    private Applicazione main;
    private UserBean currentUser;
    @FXML
    private Text tnome;
    @FXML
    private Text tcognom;
    @FXML
    private Text temail;
    @FXML
    private Text tusername;


    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }
    @FXML
    public void vaiAHome(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;
    }



    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController=new MyTripController();
        myTripController.myTrip(currentUser,main);
    }
    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }

    public void setInfo() throws IOException, SQLException, ExistsUserException {

        RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(currentUser);
        SignUpUserBean user=regLoginControllerApp.info();
        tnome.setText(user.getNome());
        tcognom.setText(user.getCognome());
        temail.setText(user.getEmail());
        tusername.setText(user.getUsername());

    }

}
