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
import javafx.scene.paint.Color;
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
    @FXML
    private Text stato;


    public void createbox(TripBean bean) {
        Image image= BookTripController.bytesToImage(bean.getImage());
        imagine.setImage(image);
        title.setText(bean.getCity());
        data.setText(bean.getDataAnd() +"/" + bean.getDataRit());
        if(bean.isStato()){
            stato.setText("Confermata");
            stato.setFill(Color.GREEN);
        }
        else{
            stato.setText("in lavorazione");

            stato.setFill(Color.RED);
        }
    }


}
