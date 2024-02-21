package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.PlacesTerminatedException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class PageTripController {
    private Applicazione main;
    private TripBean currentTrip;
    @FXML
    private ImageView immagine;
    @FXML
    private Button utente;
    @FXML
    private Text dove;
    @FXML
    private Text posti;
    @FXML
    private Text data;
    @FXML
    private Text prezzo;
    private UserBean currentUser;


    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }
    public void setCurrentUser(UserBean currentUser){
        this.currentUser = currentUser;
    }
    public void setTrip(TripBean trip){
        this.currentTrip =trip;
    }
    public void charge() {
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(currentTrip.getImage());
        image=new Image(inputStream);
        immagine.setImage(image);
        dove.setText(currentTrip.getCity());
        data.setText(currentTrip.getDataAnd() +"/" + currentTrip.getDataRit());
        prezzo.setText((int) currentTrip.getPrice()+"€");
        posti.setText(currentTrip.getAvailable()+" rimanenti");


        }
    public void setButtonText() {

        utente.setText(currentUser.getUsername());
    }


    @FXML
 private void viewTrip() throws IOException, SQLException {
        ViewTripController viewTripController=new ViewTripController();
        viewTripController.viewTrip(main, currentUser);

    }
    @FXML
    private void pagamento() throws IOException, SQLException {
        BookTripController bookTripController=new BookTripController();
        try {
            bookTripController.checkAlready(currentTrip,currentUser);

        FXMLLoader pagamentoLoader = new FXMLLoader(Applicazione.class.getResource("view1/utente/pagamento.fxml"));
        Parent pagamentoroot = pagamentoLoader.load();
        Scene pagamentoScene = new Scene(pagamentoroot);
        PagamentoController pagamento = pagamentoLoader.getController();
        pagamento.setMain(main);
        pagamento.setTrip(currentTrip);
        pagamento.setUser(currentUser);
        Stage stage = main.getStage();
        stage.setScene(pagamentoScene);
        pagamento.setButtonText();
        }
        catch (AlreadyPrenotedException e)
        {

            Alert alert3=new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("errore");
            alert3.setHeaderText(null);
            alert3.setContentText(e.getMessage());
            alert3.showAndWait();
        } catch (PlacesTerminatedException e) {
            Alert alert3=new Alert(Alert.AlertType.WARNING);
            alert3.setTitle("errore prenotazione");
            alert3.setHeaderText(null);
            alert3.setContentText(e.getMessage());
            alert3.showAndWait();
        }

    }





}

