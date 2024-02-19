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
public class ViewTripController {
    @FXML
    private Button user;
    private Applicazione main;
    @FXML
    private ListView<VBox> listaview;
    private BeanFactory currentUser;

    public void setUser(BeanFactory utente) {

        currentUser = utente;
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
        BookTripController bookTripController=new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();

        // Crea un VBox per ciascun elemento nella lista e aggiungilo alla ListView
        for (TripBean viaggio : viaggi) {
             FXMLLoader viaggioLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/viaggio.fxml"));
             VBox box=viaggioLoader.load();
             ViaggioController controller = viaggioLoader.getController();
             controller.setMain(main);
             controller.setUser(currentUser);
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
        MyTripController myTripController=new MyTripController();
        myTripController.myTrip(currentUser,main);
    }


}