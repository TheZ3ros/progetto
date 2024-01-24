package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.DAO.UserDAO;
import com.example.progetto.bean.UserBean;
import com.example.progetto.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Objects;

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
    private void HandlerLoginUtente() throws SQLException {

        UserBean utentebean = new UserBean(usernameUtente.getText(),passwordUtente.getText());
        UserDAO dao=new UserDAO();
        User utente;
        try {
           utente=dao.execute(utentebean.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (utentebean.getPassword().equals(utente.getPassword())){
            System.out.println("accesso effettuato");
            main.vai_a_UserHome(utente);
        }
        else {
            System.out.println("accesso non effettuato");

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
