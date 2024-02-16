package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MyTripController {
    @FXML
    private Button user;
    private Applicazione main;
    @FXML
    private ListView<VBox> listaview;
    private UserBean currentUser;

    public void setUser(UserBean utente) {

        currentUser = utente;
    }
    public void ricerca() throws SQLException, IOException {
        listaview.getItems().clear();
        charge();
    }
    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }
    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vaiAHome() {

        main.vaiAHome();
    }
    public void charge() throws SQLException, IOException {
        BookTripController bookTripController =new BookTripController();
        List<TripBean> viaggi = bookTripController.getTripUser(currentUser);
        for (TripBean viaggio : viaggi) {
            FXMLLoader prenotazioneLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/prenotazione.fxml"));
            VBox box=prenotazioneLoader.load();
            PrenotazioneController controller = prenotazioneLoader.getController();
            setButtonText();
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }


    }
    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page = new ViewTripController();
        page.viewTrip(main, currentUser);
        }
    }
