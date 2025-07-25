package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.AlreadyPrenotedException;
import com.ispw.progetto.exception.PlacesTerminatedException;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
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

public class PageTripController implements StageAware {

    private Stage stage;
    private TripBean currentTrip;
    private UserBean currentUser;

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

    // Setters
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCurrentUser(UserBean currentUser) {
        this.currentUser = currentUser;
    }

    public void setTrip(TripBean trip) {
        this.currentTrip = trip;
    }

    public void charge() {
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(currentTrip.getImage());
        image = new Image(inputStream);
        immagine.setImage(image);
        dove.setText(currentTrip.getCity());
        data.setText(currentTrip.getDataAnd() + "/" + currentTrip.getDataRit());
        prezzo.setText((int) currentTrip.getPrice() + "€");
        posti.setText(currentTrip.getAvailable() + " rimanenti");
    }

    public void setButtonText() {
        utente.setText(currentUser.getUsername());
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
    private void pagamento() throws IOException, SQLException {
        BookTripController bookTripController = new BookTripController();
        try {
            bookTripController.checkAlready(currentTrip, currentUser);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ispw/progetto/view1/utente/pagamento.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            PagamentoController pagamento = loader.getController();
            pagamento.setStage(stage);
            pagamento.setTrip(currentTrip);
            pagamento.setUser(currentUser);
            pagamento.setButtonText();

            stage.setScene(scene);
            stage.setTitle("Pagamento");
        } catch (AlreadyPrenotedException | PlacesTerminatedException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Errore prenotazione");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
