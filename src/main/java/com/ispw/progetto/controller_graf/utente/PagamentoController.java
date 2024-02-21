package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.Applicazione;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.exception.*;
import com.ispw.progetto.bean.BookBean;
import com.ispw.progetto.bean.BuonoBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_app.PagamentoControllerApp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class PagamentoController{

    @FXML
    private Button agency;
    @FXML
    private TextField numero;
    @FXML
    private TextField cvv;
    @FXML
    private Text timer;
    @FXML
    private Text price;
    @FXML
    private DatePicker scadenza;
    @FXML
    private TextField buono;
    private Applicazione main;
    private TripBean currentTrip;
    private UserBean currentUser;
    private int tempoRimanente=600;
    private static final String ACTION ="Informazione";

    @FXML
    public void initialize() {
        // Aggiorna il TextField del timer all'avvio
        updateTimerTextField();

        // Crea una Timeline che si aggiorna ogni secondo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timeline.setCycleCount(Timeline.INDEFINITE); // Ripeti all'infinito
        timeline.play();

        Timer timer = new Timer();
        int tempoInMillisecondi = 600000; // 5 secondi

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        ViewTripController page = new ViewTripController();
                        page.viewTrip(main, currentUser);
                    } catch (IOException | SQLException e) {
                        try {
                            throw new IOException(e.getMessage());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
        }, tempoInMillisecondi);
    }

    // Metodo chiamato ogni secondo per aggiornare il timer
    private void updateTimer(ActionEvent event) {
        tempoRimanente--;
        if (tempoRimanente >= 0) {
            updateTimerTextField();
        }
    }

    // Metodo per aggiornare il testo nel TextField del timer
    private void updateTimerTextField() {
        timer.setText("Tempo rimanente: " + tempoRimanente + " secondi");
    }
    public void setMain(Applicazione main) {

        this.main = main;
    }
    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        ViewTripController page=new ViewTripController();
        page.viewTrip(main, currentUser);

    }
    public void setUser(UserBean utente){

        currentUser=utente;
    }
    public void setTrip(TripBean trip){
        this.currentTrip=trip;
    }
    public void setButtonText() {

        agency.setText(currentUser.getUsername());
        price.setText("Totale "+ (int) currentTrip.getPrice()+"€");

    }

    @FXML
    private void submit() throws SQLException, IOException {
        String numeroCarta=numero.getText();
        String cvvCode=cvv.getText();
        LocalDate data=scadenza.getValue();

        try {

            PagamentoControllerApp pagamentoControllerApp=new PagamentoControllerApp();
            pagamentoControllerApp.checkCard(numeroCarta,cvvCode,data);
            BookBean book=new BookBean(currentUser.getUsername(),currentTrip.getId());
            BookTripController bookTripController=new BookTripController();
            bookTripController.bookTrip(book);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prenotato");
            alert.setHeaderText(null);
            alert.setContentText("prenotazione effettuata correttamente");
            alert.showAndWait();
        }
        catch(CardNotTrueException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            numero.setText(null);
        } catch (PlacesTerminatedException e) {
            Alert alert2=new Alert(Alert.AlertType.ERROR);
            alert2.setTitle(ACTION);
            alert2.setHeaderText(null);
            alert2.setContentText(e.getMessage());
            alert2.showAndWait();
        } catch (AlreadyPrenotedException e) {
            Alert alert3=new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(ACTION);
            alert3.setHeaderText(null);
            alert3.setContentText(e.getMessage());
            alert3.showAndWait();
        }


    }

    @FXML
    private void checkBuono() throws SQLException, IOException {
        String buonoSpesa = buono.getText();
        BuonoBean buonoBean=new BuonoBean();
        buonoBean.setCodice(buonoSpesa);
        PagamentoControllerApp pagamento=new PagamentoControllerApp();
        try{
            buonoBean=pagamento.checkBuono(buonoBean);
            int totale=((int)currentTrip.getPrice()-buonoBean.getValore());
            price.setText("Totale "+totale+"€");

        }
        catch(NotValidCouponException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(ACTION);
            alert.setHeaderText(null);
            alert.setContentText("Coupon non valido");
            alert.showAndWait();
            buono.setText(null);
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
