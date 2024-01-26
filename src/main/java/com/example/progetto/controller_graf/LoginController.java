package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.UserBean;
import com.example.progetto.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.progetto.controller_app.LogiinController;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    private Stage stage;

    @FXML
    private TextField usernameUtente;

    @FXML
    private PasswordField passwordUtente;

    @FXML
    public TextField usernameAgenzia;

    @FXML
    public PasswordField passwordAgenzia;

    private Applicazione main;
    private UserHomeController userhome;
    private Scene homeloginScene;


    @FXML
    private void vai_a_Home(ActionEvent event){

        main.vai_a_Home();
    }

    public void setMain(Applicazione main){

        this.main = main;

    }

    @FXML
    private void HandlerLoginUtente() throws SQLException, IOException {
        String user_utente=usernameUtente.getText();
        String pass_utente=passwordUtente.getText();
        UserBean user = new UserBean(user_utente,pass_utente);
        LogiinController login=new LogiinController(user);
        login.login_utente();
        if(user.getToken()){
            FXMLLoader UserHomeLoader = new FXMLLoader(Applicazione.class.getResource("home_login.fxml"));
            Parent UserHomeRoot = UserHomeLoader.load();
            homeloginScene = new Scene(UserHomeRoot);
            userhome = UserHomeLoader.getController();
            userhome.setMain(main);
            userhome.setUser(user);
            stage=main.getStage();
            stage.setScene(homeloginScene);
            userhome.setButtonText();
            stage.setTitle("Accedi");
        }


    }

    @FXML
    private void HandlerLoginAgenzia(){

        String user_Agenzia = usernameAgenzia.getText();
        String pass_Agenzia = passwordAgenzia.getText();
        System.out.println("Username dell'agenzia: " + user_Agenzia);
        System.out.println("Password dell'agenzia: " + pass_Agenzia);
    }
}
