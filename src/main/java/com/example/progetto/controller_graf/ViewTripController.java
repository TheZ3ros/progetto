package com.example.progetto.controller_graf;

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

public class ViewTripController {
    @FXML
    private Button user;
    private Applicazione main;
    @FXML
    private ListView listaview;
    private UserBean currentUser;

    public void setUser(UserBean utente) {

        currentUser = utente;
    }

    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }

    public void setMain(Applicazione main) {

        this.main = main;
    }

    @FXML
    private void vai_a_Home() {

        main.vai_a_Home();
    }

    public void initialize() throws SQLException, IOException {
        // Carica la lista di viaggi dal database o da qualsiasi altra sorgente
        List<TripBean> viaggi = BookTripController.show_trip();

        // Crea un VBox per ciascun elemento nella lista e aggiungilo alla ListView
        for (TripBean viaggio : viaggi) {
            FXMLLoader viaggioLoader = new FXMLLoader(Applicazione.class.getResource("viaggio.fxml"));
            VBox box=viaggioLoader.load();
            ViaggioController controller = viaggioLoader.getController();
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }
    }
}