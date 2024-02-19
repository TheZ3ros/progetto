package com.example.progetto.controller_graf.agenzia;

import com.example.progetto.Applicazione;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.GetTripStatusController;
import com.example.progetto.pattern.factory.BeanFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TripStatusController {
    private Applicazione main;
    private BeanFactory thisUser;
    private TripBean currentTrip;
    @FXML
    private ListView<VBox> listaview;
    @FXML
    private ImageView imagine;
    @FXML
    private Button nome;


    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    public void vaiAHome() {

        main.vaiAHome();
    }

    public void setThisUser(BeanFactory thisUser){
        this.thisUser = thisUser;
    }
    public void setTrip(TripBean trip){
        this.currentTrip =trip;
    }
    public void setButtonText() {

        nome.setText(thisUser.getUsername());
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        AgencyTripsController viewTripController=new AgencyTripsController();
        viewTripController.agencyTrips(main, thisUser);

    }

    public void charge() throws SQLException, IOException {
        Image image= BookTripController.bytesToImage(currentTrip.getImage());
        imagine.setImage(image);

        List<TripStatusBean> stati = GetTripStatusController.showtripstatus(currentTrip.getId());

        for(TripStatusBean stato : stati){
            FXMLLoader statusvisualizerLoader = new FXMLLoader(Applicazione.class.getResource("view1/agenzia/statusvisualizer.fxml"));
            VBox box = statusvisualizerLoader.load();
            StatusVisualizerController controller = statusvisualizerLoader.getController();
            controller.setMain(main);
            controller.setCurrentUser(thisUser);
            controller.setTrip(currentTrip);
            controller.createbox(stato);
            listaview.getItems().add(box);

        }

    }

}
