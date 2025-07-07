package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
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

    private Stage stage;           // Nuovo campo stage
    private AgencyBean user;
    private TripBean bean;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setUser(AgencyBean user){
        this.user = user;
    }

    public void createbox(TripBean bean) {
        this.bean = bean;
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bean.getImage());
        image = new Image(inputStream);
        imagine.setImage(image);
        title.setText(bean.getCity());
        data.setText(bean.getDataAnd() + "/" + bean.getDataRit());
        prezzo.setText((int)bean.getPrice() + "€");
    }

    public void tripstatus() throws IOException, SQLException {

        FXMLLoader paginaLoader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/agenzia/tripstatus.fxml"));
        Parent pageroot = paginaLoader.load();
        Scene paginaScene = new Scene(pageroot);
        TripStatusController tripstatus = paginaLoader.getController();

        // Se TripStatusController ha setStage(Stage), la puoi chiamare qui:
        tripstatus.setStage(stage);

        tripstatus.setTrip(bean);
        tripstatus.setThisUser(user);
        stage.setScene(paginaScene);
        tripstatus.charge();
        tripstatus.setButtonText();
    }
}
