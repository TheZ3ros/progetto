package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.pattern.factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ViaggioController {
    private TripBean tripBean;
    private BeanFactory userFactory;
    @FXML
    private ImageView imagine;
    @FXML
    private Button title;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    private Applicazione main;

    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setUserFactory(BeanFactory userFactory){
        this.userFactory = userFactory;
    }

    public void createbox(TripBean bean) {
        this.tripBean =bean;
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bean.getImage());
        image=new Image(inputStream);
        imagine.setImage(image);
            title.setText(bean.getCity());
            data.setText(bean.getDataAnd() +"/" + bean.getDataRit());
            prezzo.setText((int)bean.getPrice()+"€");
        }

    public void pagetrip() throws IOException {
            FXMLLoader paginaLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/pagetrip.fxml"));
            Parent pageroot = paginaLoader.load();
            Scene paginaScene = new Scene(pageroot);
            PageTripController pagetrip = paginaLoader.getController();
            pagetrip.setMain(main);
            pagetrip.setTrip(tripBean);
            pagetrip.setCurrentUser(userFactory);
            Stage stage = main.getStage();
            stage.setScene(paginaScene);
            pagetrip.charge();
            pagetrip.setButtonText();
        }
    }



