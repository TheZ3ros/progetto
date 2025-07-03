package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
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
    private UserBean userFactory;
    private Stage stage;

    @FXML
    private ImageView imagine;

    @FXML
    private Button title;

    @FXML
    private Text data;

    @FXML
    private Text prezzo;

    // Setter per lo Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUserFactory(UserBean user) {
        this.userFactory = user;
    }

    public void createbox(TripBean bean) {
        this.tripBean = bean;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bean.getImage());
        Image image = new Image(inputStream);
        imagine.setImage(image);
        title.setText(bean.getCity());
        data.setText(bean.getDataAnd() + "/" + bean.getDataRit());
        prezzo.setText((int) bean.getPrice() + "€");
    }

    public void pagetrip() throws IOException {
        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/pagetrip.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        PageTripController pagetrip = loader.getController();
        pagetrip.setStage(stage);
        pagetrip.setTrip(tripBean);
        pagetrip.setCurrentUser(userFactory);
        pagetrip.charge();
        pagetrip.setButtonText();

        stage.setScene(scene);
        stage.setTitle("Dettaglio Viaggio");
    }
}
