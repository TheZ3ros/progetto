package com.example.progetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private void vai_a_Home(ActionEvent event){
        main.vai_a_Home();
    }

    public void setMain(Applicazione main){
        this.main = main;
    }

    @FXML
    private void HandlerLoginUtente(){

        String user_Utente = usernameUtente.getText();
        String pass_Utente = passwordUtente.getText();
        System.out.println("Username dell'utente: " + user_Utente);
        System.out.println("Password dell'utente: " + pass_Utente);

    }

    @FXML
    private void HandlerLoginAgenzia(){

        String user_Agenzia = usernameAgenzia.getText();
        String pass_Agenzia = passwordAgenzia.getText();
        System.out.println("Username dell'agenzia: " + user_Agenzia);
        System.out.println("Password dell'agenzia: " + pass_Agenzia);
    }
}
