package com.ispw.progetto.controller_graf.utente;

import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.controller_graf.HomeController;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MyTripController implements StageAware {
    @FXML
    private Button user;

    @FXML
    private ListView<VBox> listaview;

    private UserBean currentUser;
    private Stage stage;

    public void setUser(UserBean utente) {
        currentUser = utente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void ricerca() throws SQLException, IOException {
        listaview.getItems().clear();
        charge();
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
        List<TripBean> viaggi = bookTripController.getTripUser(currentUser);

        for (TripBean viaggio : viaggi) {
            FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/prenotazione.fxml"));
            VBox box = loader.load();
            PrenotazioneController controller = loader.getController();
            controller.createbox(viaggio);
            listaview.getItems().add(box);
        }
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
//        ViewTripController page = new ViewTripController();
//        page.viewTrip(stage, currentUser);
    }

    public void myTrip(UserBean user, Stage stage) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/myTrip.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MyTripController controller = loader.getController();
        controller.setStage(stage);
        controller.setUser(user);
        stage.setTitle("I miei viaggi");
        stage.setScene(scene);
        controller.charge();
    }

    @FXML
    public void info() throws IOException, SQLException {
        SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/utente/info_user.fxml", this);
//        FXMLLoader loader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/utente/info_user.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        InfoUserController controller = loader.getController();
//        controller.setStage(stage);
//        controller.setUser(currentUser);
//        controller.setInfo();
//        stage.setTitle("I miei viaggi");
//        stage.setScene(scene);
    }
}
