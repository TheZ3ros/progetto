package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PageTripController {
    private Applicazione main;
    private TripBean currentTrip;
    @FXML
    private Text dove;
    @FXML
    private Text posti;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    @FXML
    private ImageView imagine;
    @FXML
    private Button nome;
    private UserBean currentUser;

    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vai_a_Home() {

        main.vaiAHome();
    }
    public void setCurrentUser(UserBean currentUser){
        this.currentUser = currentUser;
    }
    public void set_trip(TripBean trip){
        this.currentTrip =trip;
    }
    public void charge() {
        Image image= BookTripController.bytesToImage(currentTrip.getImage());
        imagine.setImage(image);
        dove.setText(currentTrip.getCity());
        data.setText(currentTrip.getDataAnd() +"/" + currentTrip.getDataRit());
        prezzo.setText((int) currentTrip.getPrice()+"€");
        posti.setText(currentTrip.getAvailable()+" rimanenti");


        }
    public void setButtonText() {

        nome.setText(currentUser.getUsername());
    }

    @FXML
    public void Booking() throws SQLException {
       int n=BookTripController.bookTrip(currentUser, currentTrip);
        switch (n){
            case 1:
                Alert alert=new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Informazione");
                alert.setHeaderText(null);
                alert.setContentText("Prenotazione effettuata correttamente.");
                alert.showAndWait();
                break;
            case 2:
                Alert alert2=new Alert(AlertType.ERROR);
                alert2.setTitle("Informazione");
                alert2.setHeaderText(null);
                alert2.setContentText("Posti terminati.");
                alert2.showAndWait();
                break;
            case 3:
                Alert alert3=new Alert(AlertType.WARNING);
                alert3.setTitle("Informazione");
                alert3.setHeaderText(null);
                alert3.setContentText("Prenotazione già effettuata.");
                alert3.showAndWait();
                break;


        }
    }
    @FXML
    private void view_trip() throws IOException, SQLException {

        view_trip(main, currentUser);

    }

    static void view_trip(Applicazione main, UserBean currentUser) throws IOException, SQLException {
        FXMLLoader ViewTripLoader = new FXMLLoader(Applicazione.class.getResource("view_trip.fxml"));
        Parent ViewTripRoot = ViewTripLoader.load();
        Scene viewTripScene = new Scene(ViewTripRoot);
        ViewTripController viewtrip = ViewTripLoader.getController();
        viewtrip.setMain(main);
        viewtrip.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(viewTripScene);
        viewtrip.setButtonText();
        viewtrip.charge();
        stage.setTitle("Ricerca");
    }

}

