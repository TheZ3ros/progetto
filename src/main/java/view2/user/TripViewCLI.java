package view2.user;

import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.CardNotTrueException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.Factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripViewCLI {
    private final BeanFactory currentUser;

    public TripViewCLI(BeanFactory user) {
        this.currentUser=user;

    }

    public void start(HomeLoginCLI login) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
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
        Printer.printMessage("Inserire l'ID del viaggio che si vuole prenotare, 0 per tornare alla pagina precedente");
        Scanner reader = new Scanner(System.in);
        int n;
        while (true) {
            n = reader.nextInt();
            if (n > viaggi.size()) {
                Printer.printMessage("inserire un numero valido");
            }
            else if(n==0){
                login.start();
            }


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

