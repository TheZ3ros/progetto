package com.example.progetto.controller_graf.agenzia;

import com.example.progetto.Applicazione;
import com.example.progetto.Exception.NotValidCouponException;
import com.example.progetto.bean.AgencyBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.TripStatusBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.controller_app.GetTripStatusController;
import com.example.progetto.controller_graf.agenzia.AgencyTripsController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TripStatusController {
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
    private AgencyBean currentUser;

    public void setMain(Applicazione main){

        this.main = main;
    }

    @FXML
    private void vaiAHome() {

        main.vaiAHome();
    }

    public void setCurrentUser(AgencyBean currentUser){
        this.currentUser = currentUser;
    }
    public void setTrip(TripBean trip){
        this.currentTrip =trip;
    }
    public void charge() throws SQLException, IOException, NotValidCouponException {
        Image image= BookTripController.bytesToImage(currentTrip.getImage());
        imagine.setImage(image);

        List<TripStatusBean> stati = GetTripStatusController.showtripstatus(currentTrip.getId());

        for(TripStatusBean bean : stati){ //da aggiornare mettendo le label
            System.out.println("Username: "+bean.getUsername());
            System.out.println("Stato: "+bean.isStatus());
        }


        /*
        dove.setText(currentTrip.getCity());
        data.setText(currentTrip.getDataAnd() +"/" + currentTrip.getDataRit());
        prezzo.setText((int) currentTrip.getPrice()+"€");
        posti.setText(currentTrip.getAvailable()+" rimanenti");
        */


    }
    public void setButtonText() {

        nome.setText(currentUser.getUsername());
    }

    @FXML
    private void viewTrip() throws IOException, SQLException {
        AgencyTripsController viewTripController=new AgencyTripsController();
        viewTripController.agencyTrips(main, currentUser);

    }
}
