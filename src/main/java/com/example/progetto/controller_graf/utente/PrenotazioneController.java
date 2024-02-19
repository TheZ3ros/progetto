package com.example.progetto.controller_graf.utente;

import com.example.progetto.bean.TripBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;


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
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bean.getImage());
        image=new Image(inputStream);
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
