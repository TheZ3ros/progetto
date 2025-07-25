package viewseconda.user;

import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookedTripCLI {
    private final UserBean currentUser;

    public BookedTripCLI(UserBean user) {
        currentUser=user;
    }

    public void start(UserHomeNavigator navigator) throws SQLException, IOException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.getTripUser(currentUser);
        for (TripBean viaggio : viaggi) {
            Printer.printMessage("Città:" + viaggio.getCity());
            Printer.printMessage("Data di partenza:" + viaggio.getDataAnd());
            Printer.printMessage("Data di ritorno:" + viaggio.getDataRit());
            Printer.printMessage("Stato prenotazione:" + viaggio.isStato());
            Printer.printMessage("---------------------------------");
        }
        navigator.goToHome();
    }

}
