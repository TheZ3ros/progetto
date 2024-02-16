package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.exception.CredentialError;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_graf.agenzia.AgencyHomeController;
import com.example.progetto.controller_graf.utente.UserHomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.progetto.controller_app.LogiinController;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
    private void vaiAHome(){

        main.vaiAHome();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    public void handlerloginutente() throws IOException, SQLException {
        String userUtente=usernameUtente.getText();
        String passUtente=passwordUtente.getText();
        UserBean user = new UserBean(userUtente,passUtente);
        LogiinController login=new LogiinController(user);
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
        catch(CredentialError e){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }

    }

    @FXML
    private void handlerloginagenzia() throws IOException, SQLException {

        String userAgenzia = usernameAgenzia.getText();
        String passAgenzia = passwordAgenzia.getText();
        AgencyBean agency = new AgencyBean(userAgenzia,passAgenzia);
        LogiinController login = new LogiinController(agency);
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
        catch(CredentialError e){

            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login fallito");
            alert.setHeaderText(null);
            alert.setContentText("Username o password errati");
            alert.showAndWait();
        }


    }
}
