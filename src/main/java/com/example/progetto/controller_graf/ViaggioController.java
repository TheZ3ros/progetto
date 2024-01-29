package com.example.progetto.controller_graf;

import com.example.progetto.bean.TripBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Objects;

public class ViaggioController {
    @FXML
    private ImageView imagine;
    @FXML
    private Button title;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    @FXML
    private Text posti;



    public void createbox(TripBean bean) {

        imagine.setImage(new Image(Objects.requireNonNull(getClass().getResource(bean.image())).toExternalForm()));
            title.setText(bean.getCity());
            data.setText(bean.getData_and() +"/" + bean.getData_rit());
            prezzo.setText((int)bean.getPrice()+"€");
        //    posti.setText("posti disponibili:" + bean.getPlaces());
        //  prenota.setText("prenota");
        }
    public void pagetrip(){
        System.out.println("cliccato");
    }

    }


