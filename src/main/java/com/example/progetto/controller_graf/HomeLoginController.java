package com.example.progetto.controller_graf;
import com.example.progetto.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class HomeLoginController {
    @FXML
    private Button username;
    private User currentUser;
    public HomeLoginController(User user) {
        this.currentUser = user;
    }
    public void initialize() {
        // Inizializza il testo del bottone dinamicamente
        username.setText(currentUser.getUsername());
    }


}
