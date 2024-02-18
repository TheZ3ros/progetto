package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.PasswordIllegalException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrazioneController {
    @FXML
private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;


    private Applicazione main;


    @FXML
    private void vaiAHome(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    public void registrazioneutente() throws IOException, SQLException {
        String userUtente=usernameUtente.getText();
        String passUtente=passwordUtente.getText();
        UserBean user = new UserBean(userUtente,passUtente);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(user);
            regLoginControllerApp.registrazione();
        }
        catch(PasswordIllegalException e){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrazione fallita");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }


}
