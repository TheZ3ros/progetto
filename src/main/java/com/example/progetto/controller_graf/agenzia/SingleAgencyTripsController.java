package com.example.progetto.controller_graf.agenzia;

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

import java.io.IOException;
import java.sql.SQLException;

public class SingleAgencyTripsController {
    @FXML
    private ImageView imagine;
    @FXML
    private Button title;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    private Applicazione main;
    private BeanFactory user;
    private TripBean bean;


    @FXML
    public void setMain(Applicazione main){

        this.main = main;
    }

    public void setUser(BeanFactory user){
        this.user=user;
    }

    public void createbox(TripBean bean) {
        this.bean=bean;
        Image image= BookTripController.bytesToImage(bean.getImage());
        imagine.setImage(image);
        title.setText(bean.getCity());
        data.setText(bean.getDataAnd() +"/" + bean.getDataRit());
        prezzo.setText((int)bean.getPrice()+"€");
    }

    public void tripstatus() throws IOException, SQLException {
        FXMLLoader paginaLoader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/tripstatus.fxml"));
        Parent pageroot = paginaLoader.load();
        Scene paginaScene = new Scene(pageroot);
        TripStatusController tripstatus = paginaLoader.getController();
        tripstatus.setMain(main);
        tripstatus.setTrip(bean);
        tripstatus.setThisUser(user);
        Stage stage = main.getStage();
        stage.setScene(paginaScene);
        tripstatus.charge();
        tripstatus.setButtonText();
    }
}
