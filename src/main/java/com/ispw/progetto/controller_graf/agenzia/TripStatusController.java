package com.ispw.progetto.controller_graf.agenzia;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.TripStatusBean;
import com.ispw.progetto.utils.SceneNavigator;
import com.ispw.progetto.utils.StageAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TripStatusController implements StageAware {
    private Stage stage;
    private AgencyBean thisUser;
    private TripBean currentTrip;

    @FXML
    private ListView<VBox> listaview;

    @FXML
    private ImageView imagine;

    @FXML
    private Button nome;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setThisUser(AgencyBean thisUser){
        this.thisUser = thisUser;
    }

    public void setTrip(TripBean trip){
        this.currentTrip = trip;
    }

    public void setButtonText() {
        nome.setText(thisUser.getUsername());
    }

    @FXML
    private void viewTrip() {
        //temporaneamente vuoto in attesa di aggiornamento sulla View
    }

    public void charge() throws SQLException, IOException {
        Image image;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(currentTrip.getImage());
        image = new Image(inputStream);
        imagine.setImage(image);

        List<TripStatusBean> stati = com.ispw.progetto.controller_app.TripStatusController.showtripstatus(currentTrip.getId());

        for (TripStatusBean stato : stati){
            FXMLLoader statusvisualizerLoader = new FXMLLoader(com.ispw.progetto.Applicazione.class.getResource("view1/agenzia/statusvisualizer.fxml"));
            VBox box = statusvisualizerLoader.load();
            StatusVisualizerController controller = statusvisualizerLoader.getController();
            controller.setStage();
            controller.setCurrentUser();
            controller.setTrip(currentTrip);
            controller.createbox(stato);
            listaview.getItems().add(box);
        }
    }

    @FXML
    public void vaiAHome() {
        // Qui puoi caricare la home agency passando stage e user
        try {
            SceneNavigator.switchTo(stage, "/com/ispw/progetto/view1/home.fxml", this);
        } catch (IOException e) {
            e.printStackTrace();
            // gestisci errore
        }
    }
}
