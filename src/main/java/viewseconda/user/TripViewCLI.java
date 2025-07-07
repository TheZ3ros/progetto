package viewseconda.user;

import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.*;
import com.ispw.progetto.utils.AppContext;
import com.ispw.progetto.utils.PersistenceMode;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripViewCLI {
    private final UserBean currentUser;

    public TripViewCLI(UserBean user) {
        this.currentUser = user;
    }

    private void displayTrips(List<TripBean> trips) {
        AppContext appContext = AppContext.getInstance();

        for (TripBean trip : trips) {
            if(appContext.getPersistenceMode() == PersistenceMode.MEMORY){
                Printer.printMessage("ID: 1");
            }
            else{
                Printer.printMessage("ID:" + trip.getId());
            }
            Printer.printMessage("Città:" + trip.getCity());
            Printer.printMessage("Prezzo:" + trip.getPrice());
            Printer.printMessage("Data di partenza:" + trip.getDataAnd());
            Printer.printMessage("Data di ritorno:" + trip.getDataRit());
            Printer.printMessage("Posti disponibili:" + trip.getAvailable());
            Printer.printMessage("---------------------------------");
        }
    }

    public void viewtrip(UserHomeNavigator navigator) throws SQLException, IOException {
        BookTripController bookTripController = new BookTripController(); // 🔹

        List<TripBean> viaggi = bookTripController.showTrip();
        displayTrips(viaggi);

        Scanner reader = new Scanner(System.in);
        while (true) {
            Printer.printMessage("Inserire l'ID del viaggio (0 per tornare, 10 per filtrare):");
            int n = reader.nextInt();

            if (n == 10) {
                Scanner scanner = new Scanner(System.in);
                Printer.printMessage("Inserire nome città:");
                String citta = scanner.nextLine();
                SearchBean searchBean = new SearchBean();
                searchBean.setCitta(citta);
                try {
                    viaggi = bookTripController.searchByCity(searchBean);
                    displayTrips(viaggi);
                } catch (FailedSearchException e) {
                    Printer.printMessage(e.getMessage());
                }
            } else if (n == 0) {
                navigator.goToHome();
                return;
            } else if (n > viaggi.size()) {
                Printer.printMessage("ID non valido");
            } else {
                try {
                    new PaymentCLI(viaggi.get(n - 1), currentUser).start(navigator); // 🔹
                    break;
                } catch (CardNotTrueException e) {
                    Printer.printMessage(e.getMessage());
                    navigator.goToHome();
                }
            }
        }
    }
}


