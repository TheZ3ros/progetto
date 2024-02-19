package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.exception.CredentialErrorException;
import com.example.progetto.controller_graf.agenzia.AgencyHomeController;
import com.example.progetto.controller_graf.utente.UserHomeController;
import com.example.progetto.pattern.factory.BeanFactory;
import com.example.progetto.pattern.factory.Factory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.progetto.controller_app.RegLoginControllerApp;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;

    @FXML
    public TextField usernameAgenzia;

    @FXML
    public PasswordField passwordAgenzia;

    private Applicazione main;


    @FXML
    public void vaiAHome(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    public void handlerloginutente() throws Exception {
        String userUtente=usernameUtente.getText();
        String passUtente=passwordUtente.getText();
        Factory factory=new Factory();
        BeanFactory user= factory.createBean(1);
        user.setUsername(userUtente);
        user.setPassword(passUtente);

        RegLoginControllerApp login=new RegLoginControllerApp(user);
        try{
            login.loginUtente();
            FXMLLoader userhomeloader = new FXMLLoader(Applicazione.class.getResource("view1/utente/home_login.fxml"));
            Parent userhomeroot = userhomeloader.load();
            Scene homeloginScene = new Scene(userhomeroot);
            UserHomeController userhome = userhomeloader.getController();
            userhome.setMain(main);
            userhome.setUser(user);
            Stage stage = main.getStage();
            stage.setScene(homeloginScene);
            userhome.setButtonText();
            stage.setTitle("Accedi");
        }
        catch(CredentialErrorException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void handlerloginagenzia() throws Exception {

        String userAgenzia = usernameAgenzia.getText();
        String passAgenzia = passwordAgenzia.getText();
        Factory factory=new Factory();
        BeanFactory agency= factory.createBean(2);
        agency.setUsername(userAgenzia);
        agency.setPassword(passAgenzia);
        RegLoginControllerApp login = new RegLoginControllerApp(agency);
        try{
            login.loginAgenzia();
            FXMLLoader agencyhomeloader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/agency_home.fxml"));
            Parent agencyhomeroot = agencyhomeloader.load();
            Scene homeloginScene = new Scene(agencyhomeroot);
            AgencyHomeController agencyhome = agencyhomeloader.getController();
            agencyhome.setMain(main);
            agencyhome.setUser(agency);
            Stage stage = main.getStage();
            stage.setScene(homeloginScene);
            agencyhome.setButtonText();
            stage.setTitle("Home Agenzia");
        }
        catch(CredentialErrorException e){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }


    }
    @FXML
    private void registrati() throws IOException {
        FXMLLoader regloader = new FXMLLoader(Applicazione.class.getResource("view1/registrazione.fxml"));
        Parent regroot = regloader.load();
        Scene regScene = new Scene(regroot);
        RegistrazioneController reghome = regloader.getController();
        reghome.setMain(main);
        Stage stage = main.getStage();
        stage.setScene(regScene);
        stage.setTitle("Registrazione");
    }
}
