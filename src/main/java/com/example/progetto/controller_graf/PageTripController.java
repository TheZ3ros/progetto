package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class PageTripController {
    private Applicazione main;
    private TripBean trip;
    @FXML
    private Text dove;
    @FXML
    private Text posti;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    @FXML
    private ImageView imagine;
    @FXML
    private Button nome;
    private UserBean userBean;
    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vai_a_Home() {

        main.vai_a_Home();
    }
    public void setUserBean(UserBean userBean){
        this.userBean = userBean;
    }
    public void set_trip(TripBean trip){
        this.trip=trip;
    }
    public void charge() throws SQLException, IOException {

        imagine.setImage(new Image(Objects.requireNonNull(getClass().getResource(trip.image())).toExternalForm()));
        dove.setText(trip.getCity());
        data.setText(trip.getData_and() +"/" + trip.getData_rit());
        prezzo.setText((int)trip.getPrice()+"€");
        posti.setText(trip.getPlaces()+" rimanenti");


        }
    public void setButtonText() {

        nome.setText(userBean.getUsername());
    }

    }

