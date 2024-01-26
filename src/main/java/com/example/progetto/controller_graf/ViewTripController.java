package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.DAO.TripDAO;
import com.example.progetto.bean.UserBean;
import com.example.progetto.entity.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ViewTripController {
    @FXML
    private Button user;
    private Applicazione main;
    private UserBean currentUser;
    @FXML
    private ListView <Trip> lista;
    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }
    public void setMain(Applicazione main){

        this.main = main;
    }
    @FXML
    private void vai_a_Home(){

        main.vai_a_Home();
    }
    public void initialize() {
        // Inizializza la ListView
        initListView();
    }
    private void initListView() {
        TripDAO tripDAO=new TripDAO();

    }
}
