package view2.user;

import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookedTripCLI {
    private final UserBean currentUser;

    public BookedTripCLI(UserBean user) {
        currentUser=user;
    }

    public void start(HomeLoginCLI login) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        BookTripController bookTripController=new BookTripController();
        List<TripBean> viaggi= bookTripController.getTripUser(currentUser);
        for (TripBean viaggio:viaggi){
            System.out.println("Città:"+viaggio.getCity());
            System.out.println("Data di partenza:"+viaggio.getDataAnd());
            System.out.println("Data di ritorno:"+viaggio.getDataRit());
            System.out.println("Stato prenotazione:"+viaggio.isStato());
            System.out.println("---------------------------------");
        }
        login.start();

    }
}
