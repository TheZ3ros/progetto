package com.ispw.progetto.controller_graf;

import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.SignUpUserBean;
import com.ispw.progetto.controller_app.RegLoginControllerApp;
import com.ispw.progetto.exception.ExistsUserException;
import com.ispw.progetto.exception.PasswordIllegalException;
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
    @FXML
    private TextField nome;
    @FXML
    private TextField cognome;
    @FXML
    private TextField email;



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

        String userUtente =usernameUtente.getText();
        String passUtente=passwordUtente.getText();
        String nomeUser=nome.getText();
        String cognomeUser=cognome.getText();
        String emailUser=email.getText();
        if (userUtente.isEmpty() || passUtente.isEmpty() || nomeUser.isEmpty() || cognomeUser.isEmpty() || emailUser.isEmpty()) {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Registrazione");
            alert.setHeaderText(null);
            alert.setContentText("Completa tutti i campi");
            alert.showAndWait();
        }
        SignUpUserBean signUpUserBean=new SignUpUserBean();
        signUpUserBean.setCognome(cognomeUser);
        signUpUserBean.setEmail(emailUser);
        signUpUserBean.setNome(nomeUser);
        signUpUserBean.setUsername(userUtente);
        signUpUserBean.setPassword(passUtente);


        try{
            RegLoginControllerApp regLoginControllerApp=new RegLoginControllerApp(signUpUserBean);
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
