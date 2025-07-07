package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgencyTripsController implements StageAware {

    @FXML
    private Button agency;
    private AgencyBean currentUser;
    private Stage stage;  // ora uno Stage al posto di Applicazione
    @FXML
    private ListView<VBox> listaview;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(AgencyBean utente){
        currentUser = utente;
    }

    public void setButtonText() {
        agency.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() {
        try {
            SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
        } catch (IOException e) {
            Logger.getLogger(AgencyTripsController.class.getName()).log(Level.SEVERE, "Errore durante il caricamento della home agency", e);
            // Eventualmente mostra un messaggio all'utente
        }
    }


    public void charge() throws SQLException, IOException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();

        listaview.getItems().clear();

        for (TripBean viaggio : viaggi) {
            FXMLLoader getagencytripsLoader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/agenzia/singleagencytrip.fxml"));
            VBox box = getagencytripsLoader.load();
            SingleAgencyTripsController controller = getagencytripsLoader.getController();
            controller.setStage(stage);
            controller.setUser(currentUser);
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }
    }

    public void agencyTrips(Stage stage, AgencyBean currentUser) throws IOException, SQLException {
        FXMLLoader agencytripsLoader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/agenzia/agencytrips.fxml"));
        Parent agencytripsroot = agencytripsLoader.load();
        Scene agencytripscene = new Scene(agencytripsroot);
        AgencyTripsController agencytrips = agencytripsLoader.getController();
        agencytrips.setStage(stage);
        agencytrips.setUser(currentUser);
        stage.setScene(agencytripscene);
        agencytrips.setButtonText();
        stage.setTitle("Itinerari agenzia");
        agencytrips.charge();
    }
}
