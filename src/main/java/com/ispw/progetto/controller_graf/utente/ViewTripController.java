package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.FailedSearchException;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ViewTripController implements StageAware {

    @FXML
    private Button user;

    @FXML
    private ListView<VBox> listaview;

    @FXML
    private TextField cercaCitta;

    private Stage stage;
    private UserBean currentUser;

    // Setter
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(UserBean utente) {
        currentUser = utente;
    }

    public void setButtonText() {
        user.setText(currentUser.getUsername());
    }

    @FXML
    public void vaiAHome() throws IOException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
    }

    public void charge() throws SQLException, IOException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();

        for (TripBean viaggio : viaggi) {
            FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/viaggio.fxml"));
            VBox box = loader.load();
            ViaggioController controller = loader.getController();
            controller.setStage(stage);
            controller.setUserFactory(currentUser);
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }
    }

    public void ricerca() throws SQLException, IOException {
        listaview.getItems().clear();
        charge();
    }

    public void viewTrip(Stage stage, UserBean user) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/view_trip.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ViewTripController controller = loader.getController();
        controller.setStage(stage);
        controller.setUser(user);
        controller.setButtonText();
        controller.charge();

        stage.setScene(scene);
        stage.setTitle("Ricerca");
    }

    @FXML
    public void myTrip() throws SQLException, IOException {
        MyTripController myTripController = new MyTripController();
        myTripController.myTrip(currentUser, stage);
    }

    @FXML
    public void ricercaCitta() throws SQLException, IOException {
        String citta = cercaCitta.getText();
        SearchBean searchBean = new SearchBean();
        searchBean.setCitta(citta);

        BookTripController bookTripController = new BookTripController();

        try {
            List<TripBean> viaggi = bookTripController.searchByCity(searchBean);
            listaview.getItems().clear();

            for (TripBean viaggio : viaggi) {
                FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/viaggio.fxml"));
                VBox box = loader.load();
                ViaggioController controller = loader.getController();
                controller.setStage(stage);
                controller.setUserFactory(currentUser);
                controller.createbox(viaggio);
                listaview.getItems().add(box);
            }

        } catch (FailedSearchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            cercaCitta.setText(null);
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
}
