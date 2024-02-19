package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.pattern.Factory.BeanFactory;
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

public class MyTripController {
    @FXML
    private Button user;
    private Applicazione main;
    @FXML
    private ListView<VBox> listaview;
    private BeanFactory currentUser;

    public void setUser(BeanFactory utente) {

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
    public void myTrip(BeanFactory user, Applicazione main) throws SQLException, IOException {
        FXMLLoader myTripLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/myTrip.fxml"));
        Parent mytriproot = myTripLoader.load();
        Scene myTripScene = new Scene(mytriproot);
        MyTripController myTripController = myTripLoader.getController();
        myTripController.setMain(main);
        myTripController.setUser(user);
        Stage stage = main.getStage();
        stage.setTitle("I miei viaggi");
        stage.setScene(myTripScene);
        myTripController.charge();

    }

}
