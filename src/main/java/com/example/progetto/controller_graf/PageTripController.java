package com.example.progetto.controller_graf;

import com.example.progetto.Applicazione;
import com.example.progetto.Exception.AlreadyPrenotedException;
import com.example.progetto.Exception.PlacesTerminatedException;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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

    private static final String ACTION ="Informazione";

    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vaiAHome() {

        main.vaiAHome();
    }
    public void setCurrentUser(UserBean currentUser){
        this.currentUser = currentUser;
    }
    public void setTrip(TripBean trip){
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
    public void booking() throws PlacesTerminatedException, SQLException, AlreadyPrenotedException, IOException {
       try{
           int n=BookTripController.bookTrip(currentUser, currentTrip);
       }
       catch(PlacesTerminatedException e){
           Alert alert2=new Alert(AlertType.ERROR);
           alert2.setTitle(ACTION);
           alert2.setHeaderText(null);
           alert2.setContentText("Posti terminati.");
           alert2.showAndWait();
        }
       catch(AlreadyPrenotedException e){
           Alert alert3=new Alert(AlertType.WARNING);
           alert3.setTitle(ACTION);
           alert3.setHeaderText(null);
           alert3.setContentText("Prenotazione già effettuata.");
           alert3.showAndWait();
       }
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle(ACTION);
        alert.setHeaderText(null);
        alert.setContentText("Prenotazione effettuata correttamente.");
        alert.showAndWait();
      /*  switch (n){
            case 1:
                Alert alert=new Alert(AlertType.CONFIRMATION);
                alert.setTitle(ACTION);
                alert.setHeaderText(null);
                alert.setContentText("Prenotazione effettuata correttamente.");
                alert.showAndWait();
                break;
            case 2:
                Alert alert2=new Alert(AlertType.ERROR);
                alert2.setTitle(ACTION);
                alert2.setHeaderText(null);
                alert2.setContentText("Posti terminati.");
                alert2.showAndWait();
                break;
            case 3:
                Alert alert3=new Alert(AlertType.WARNING);
                alert3.setTitle(ACTION);
                alert3.setHeaderText(null);
                alert3.setContentText("Prenotazione già effettuata.");
                alert3.showAndWait();
                break;
            default:
                throw new IllegalArgumentException("Valore non valido");


        }*/
    }
    @FXML
 private void viewTrip() throws IOException, SQLException {
        ViewTripController viewTripController=new ViewTripController();
        viewTripController.viewTrip(main, currentUser);

    }




}

