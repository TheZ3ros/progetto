package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.Exception.AlreadyPrenotedException;
import com.example.progetto.Exception.PlacesTerminatedException;
import com.example.progetto.bean.BookBean;
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
    public void booking() throws SQLException, IOException {
        BookBean booking=new BookBean(currentUser.getUsername(),currentTrip.getId());
       try{
           BookTripController.bookTrip(booking);
           Alert alert=new Alert(AlertType.CONFIRMATION);
           alert.setTitle(ACTION);
           alert.setHeaderText(null);
           alert.setContentText("Prenotazione effettuata correttamente.");
           alert.showAndWait();
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

    }
    @FXML
 private void viewTrip() throws IOException, SQLException {
        ViewTripController viewTripController=new ViewTripController();
        viewTripController.viewTrip(main, currentUser);

    }
    @FXML
    private void Pagamento() throws IOException{
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





}

