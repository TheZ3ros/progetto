package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.controller_app.RegLoginControllerApp;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PasswordIllegalException;
import com.example.progetto.pattern.factory.BeanFactory;
import com.example.progetto.pattern.factory.Factory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrazioneController {
    @FXML
private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;


    private Applicazione main;


    @FXML
    public void vaiAHome(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    public void registrazioneutente() throws Exception {
        String userUtente=usernameUtente.getText();
        String passUtente=passwordUtente.getText();
        Factory factory=new Factory();
        BeanFactory user= factory.createBean(1);
        user.setPassword(userUtente);
        user.setPassword(passUtente);

        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(user);
            regLoginControllerApp.registrazione();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrazione");
            alert.setHeaderText(null);
            alert.setContentText("Registrazione avvenuta con successo");
            alert.showAndWait();
        }
        catch(PasswordIllegalException | ExistsUserException e){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrazione fallita");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    @FXML
    private void vaiALogin() throws IOException {

        FXMLLoader loginLoader = new FXMLLoader(Applicazione.class.getResource("view1/login.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot);
        LoginController loginController = loginLoader.getController();
        loginController.setMain(main);
        Stage stage = main.getStage();
        stage.setScene(loginScene);
        stage.setTitle("Accedi");
    }


}
