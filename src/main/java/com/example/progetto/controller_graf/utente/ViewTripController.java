package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.SearchBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.FailedSearchException;
import com.example.progetto.pattern.factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class ViewTripController {
    @FXML
    private Button user;
    private Applicazione main;
    @FXML
    private ListView<VBox> listaview;
    private BeanFactory currentUser;
    @FXML
    private TextField cercaCitta;

    public void setUser(BeanFactory utente) {

        currentUser = utente;
    }

    public void setButtonText() {

        user.setText(currentUser.getUsername());
    }

    public void setMain(Applicazione main) {

        this.main = main;
    }

    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }

    public void charge() throws SQLException, IOException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();

        // Crea un VBox per ciascun elemento nella lista e aggiungilo alla ListView
        for (TripBean viaggio : viaggi) {
            FXMLLoader viaggioLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/viaggio.fxml"));
            VBox box = viaggioLoader.load();
            ViaggioController controller = viaggioLoader.getController();
            controller.setMain(main);
            controller.setUserFactory(currentUser);
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }

    }

    public void ricerca() throws SQLException, IOException {
        listaview.getItems().clear();
        charge();
    }

    public void viewTrip(Applicazione main, BeanFactory currentUser) throws IOException, SQLException {
        FXMLLoader viewtriploader = new FXMLLoader(Applicazione.class.getResource("view1/utente/view_trip.fxml"));
        Parent viewtriproot = viewtriploader.load();
        Scene viewTripScene = new Scene(viewtriproot);
        ViewTripController viewtrip = viewtriploader.getController();
        viewtrip.setMain(main);
        viewtrip.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(viewTripScene);
        viewtrip.setButtonText();
        viewtrip.charge();
        stage.setTitle("Ricerca");
    }

    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController = new MyTripController();
        myTripController.myTrip(currentUser, main);
    }

    @FXML
    public void ricercaCitta() throws SQLException, IOException, FailedSearchException {
        String citta = cercaCitta.getText();
        SearchBean searchBean = new SearchBean();
        searchBean.setCitta(citta);
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi;
        try {
            viaggi = bookTripController.searchByCity(searchBean);
            listaview.getItems().clear();


            // Crea un VBox per ciascun elemento nella lista e aggiungilo alla ListView
            for (TripBean viaggio : viaggi) {
                FXMLLoader viaggioLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/viaggio.fxml"));
                VBox box = viaggioLoader.load();
                ViaggioController controller = viaggioLoader.getController();
                controller.setMain(main);
                controller.setUserFactory(currentUser);
                controller.createbox(viaggio);
                listaview.getItems().add(box);
            }

        } catch (FailedSearchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            cercaCitta.setText(null);
        }


    }
    @FXML
    public void info() throws IOException, SQLException, ExistsUserException {
        FXMLLoader infoLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/info_user.fxml"));
        Parent inforoot = infoLoader.load();
        Scene myTripScene = new Scene(inforoot);
        InfoUserController infoController = infoLoader.getController();
        infoController.setMain(main);
        infoController.setUser(currentUser);
        infoController.setInfo();
        Stage stage = main.getStage();
        stage.setTitle("I miei viaggi");
        stage.setScene(myTripScene);

    }
}