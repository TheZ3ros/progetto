package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PrenotazioneController {
    @FXML
    private ImageView imagine;
    @FXML
    private Button title;
    @FXML
    private Text data;
    private Text stato;
    private Applicazione main;
    private TripBean bean;
    private UserBean user;
    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setUser(UserBean user){
        this.user=user;
    }

    public void createbox(TripBean bean) {
        this.bean=bean;
        Image image= BookTripController.bytesToImage(bean.getImage());
        imagine.setImage(image);
        title.setText(bean.getCity());
        data.setText(bean.getDataAnd() +"/" + bean.getDataRit());
    }


}
