package com.example.progetto.controller_graf.utente;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.pattern.factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private BeanFactory currentUser;


    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }
    public void setCurrentUser(BeanFactory currentUser){
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
    private void pagamento() throws IOException{
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

