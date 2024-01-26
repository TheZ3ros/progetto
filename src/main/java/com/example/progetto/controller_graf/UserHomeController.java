package com.example.progetto.controller_graf;
import com.example.progetto.Applicazione;
import com.example.progetto.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class UserHomeController {
    @FXML
    private Button user;
    private Applicazione main;
    private User currentUser;

    public void setUser(User utente){

        currentUser=utente;
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }


    public void setMain(Applicazione main){
        this.main = main;
    }
    @FXML
    private void vai_a_Home(ActionEvent event){
        main.vai_a_Home();
    }
    @FXML
    private void view_trip(ActionEvent event){
        main.vai_a_Home();
    }

}
