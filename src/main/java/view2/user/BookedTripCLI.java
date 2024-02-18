package view2.user;

import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;
import view2.Printer;

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
            Printer.printMessage("Città:"+viaggio.getCity());
            Printer.printMessage("Data di partenza:"+viaggio.getDataAnd());
            Printer.printMessage("Data di ritorno:"+viaggio.getDataRit());
            Printer.printMessage("Stato prenotazione:"+viaggio.isStato());
            Printer.printMessage("---------------------------------");
        }
        login.start();

    }
}
