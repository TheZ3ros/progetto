package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

