package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.exception.*;
import com.ispw.progetto.bean.BookBean;
import com.ispw.progetto.bean.BuonoBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_app.PagamentoControllerApp;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.animation.Animation;
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

public class PagamentoController implements StageAware {

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

    private Stage stage;
    private TripBean currentTrip;
    private UserBean currentUser;

    private int tempoRimanente = 600; // in secondi
    private static final String ACTION = "Informazione";

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(UserBean utente) {
        this.currentUser = utente;
    }

    public void setTrip(TripBean trip) {
        this.currentTrip = trip;
    }

    public void setButtonText() {
        agency.setText(currentUser.getUsername());
        price.setText("Totale " + (int) currentTrip.getPrice() + "€");
    }

    @FXML
    public void initialize() {
        updateTimerTextField();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timer timerr = new Timer();
        timerr.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
                    } catch (IOException e) {
                        showError("Errore", "Si è verificato un errore durante il ritorno alla schermata home");
                    }
                });
            }
        }, 600_000); // 10 minuti
    }

    private void updateTimer(ActionEvent event) {
        tempoRimanente--;
        if (tempoRimanente >= 0) {
            updateTimerTextField();
        }
    }

    private void updateTimerTextField() {
        timer.setText("Tempo rimanente: " + tempoRimanente + " secondi");
    }

    @FXML
    public void vaiAHome() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
    }

    @FXML
    private void viewTrip() {
        //temporaneamente vuoto in attesa di aggiornamento sulla View
    }

    @FXML
    private void submit() throws SQLException, IOException {
        String numeroCarta = numero.getText();
        String cvvCode = cvv.getText();
        LocalDate data = scadenza.getValue();

        try {
            PagamentoControllerApp pagamentoControllerApp = new PagamentoControllerApp();
            pagamentoControllerApp.checkCard(numeroCarta, cvvCode, data);

            BookBean book = new BookBean(currentUser.getUsername(), currentTrip.getId());
            BookTripController bookTripController = new BookTripController();
            bookTripController.bookTrip(book);

            showInfo();
        } catch (CardNotTrueException e) {
            showError("Errore", e.getMessage());
            numero.setText(null);
        } catch (AlreadyPrenotedException e) {
            showWarning(e.getMessage());
        }
    }

    @FXML
    private void checkBuono() throws SQLException, IOException {
        String codice = buono.getText();
        BuonoBean buonoBean = new BuonoBean();
        buonoBean.setCodice(codice);
        PagamentoControllerApp pagamento = new PagamentoControllerApp();

        try {
            buonoBean = pagamento.checkBuono(buonoBean);
            int totale = (int) currentTrip.getPrice() - buonoBean.getValore();
            price.setText("Totale " + totale + "€");
        } catch (NotValidCouponException e) {
            showError(ACTION, "Coupon non valido");
            buono.setText(null);
        }
    }

    @FXML
    public void info() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/info_user.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        InfoUserController controller = loader.getController();
        controller.setStage(stage);
        controller.setUser(currentUser);
        controller.setInfo();
        stage.setScene(scene);
        stage.setTitle("I miei viaggi");
    }

    // Utility alert
    private void showError(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

    private void showWarning(String message) {
        showAlert(Alert.AlertType.WARNING, PagamentoController.ACTION, message);
    }

    private void showInfo() {
        showAlert(Alert.AlertType.INFORMATION, "Prenotato", "Prenotazione effettuata correttamente");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
