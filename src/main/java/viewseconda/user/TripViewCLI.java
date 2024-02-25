package viewseconda.user;

import com.ispw.progetto.bean.SearchBean;
import com.ispw.progetto.bean.TripBean;
import com.ispw.progetto.bean.UserBean;
import com.ispw.progetto.controller_app.BookTripController;
import com.ispw.progetto.exception.*;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripViewCLI {
    private final UserBean currentUser;

    public TripViewCLI(UserBean user) {
        this.currentUser=user;

    }

    private void displayTrips(List<TripBean> trips) {
        for (TripBean trip : trips) {
            Printer.printMessage("ID:" + trip.getId());
            Printer.printMessage("Città:" + trip.getCity());
            Printer.printMessage("Prezzo:" + trip.getPrice());
            Printer.printMessage("Data di partenza:" + trip.getDataAnd());
            Printer.printMessage("Data di ritorno:" + trip.getDataRit());
            Printer.printMessage("Posti disponibili:" + trip.getAvailable());
            Printer.printMessage("---------------------------------");
        }
    }


    public void viewtrip(HomeLoginCLI login) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException, ExistsUserException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();
        for (TripBean viaggio : viaggi) {
            Printer.printMessage("ID:" + viaggio.getId());
            Printer.printMessage("Città:" + viaggio.getCity());
            Printer.printMessage("Prezzo:" + viaggio.getPrice());
            Printer.printMessage("Data di partenza:" + viaggio.getDataAnd());
            Printer.printMessage("Data di ritorno:" + viaggio.getDataRit());
            Printer.printMessage("Posti disponibili:" + viaggio.getAvailable());
            Printer.printMessage("---------------------------------");
        }
        Printer.printMessage("Inserire l'ID del viaggio che si vuole prenotare, 0 per tornare alla pagina precedente, 10 se si vuole filtrare per città");
        Scanner reader = new Scanner(System.in);
        int n;
        while (true) {
            n = reader.nextInt();
            if(n==10) {
                Scanner scanner = new Scanner(System.in);
                Printer.printMessage("Inserire nome città");
                String citta;
                citta = scanner.nextLine();
                SearchBean searchBean = new SearchBean();
                searchBean.setCitta(citta);
                viaggi.clear();
                try {
                    viaggi = bookTripController.searchByCity(searchBean);

                displayTrips(viaggi);
                } catch (FailedSearchException e) {
                    Printer.printMessage(e.getMessage());
                }
                viewtrip(login);

            }

            else if (n > viaggi.size()) {
                Printer.printMessage("inserire un numero valido");
            }
            else if(n==0){
                login.start();
            }
            else{
                try {
                    PaymentCLI paymentCLI=new PaymentCLI(viaggi.get(n-1),currentUser);
                    paymentCLI.start(login);
                    break;
                } catch (CardNotTrueException e) {
                    Printer.printMessage(e.getMessage());
                    login.start();
                }
            }



        }
    }
    }

