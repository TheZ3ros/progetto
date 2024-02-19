package view2.user;

import com.example.progetto.bean.TripBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.ExistsUserException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookedTripCLI {
    private final BeanFactory currentUser;

    public BookedTripCLI(BeanFactory user) {
        currentUser=user;
    }

    public void start(HomeLoginCLI login) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException, ExistsUserException {
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
