package com.example.progetto.controller_graf;

import com.example.progetto.bean.TripBean;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViaggioController {
    @FXML
    private ImageView imagine;

    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    @FXML
    private Text posti;

    @FXML
    private VBox viaggioVBox;

    @FXML
    private Text title;

    public VBox createbox(TripBean bean) {

 //           imagine.setImage(new Image(bean.image()));
            title.setText(bean.getCity());
            data.setText("dal " + bean.getData_and() + "al " + bean.getData_rit());
            prezzo.setText("prezzo totale:" + bean.getPrice());
            posti.setText("posti disponibili:" + bean.getData_and());
            VBox viaggioVbox = new VBox(imagine, title, data,prezzo,posti);
            return viaggioVbox;
        }


    }


