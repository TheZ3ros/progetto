package view2.user;

import com.example.progetto.bean.BookBean;
import com.example.progetto.bean.TripBean;
import com.example.progetto.bean.UserBean;
import com.example.progetto.controller_app.BookTripController;
import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TripViewCLI {
    private final UserBean currentUser;

    public TripViewCLI(UserBean user) {
        this.currentUser=user;

    }

    public void start(HomeLoginCLI login) throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        BookTripController bookTripController = new BookTripController();
        List<TripBean> viaggi = bookTripController.showTrip();
        for (TripBean viaggio : viaggi) {
            System.out.println("ID:" + viaggio.getId());
            System.out.println("Città:" + viaggio.getCity());
            System.out.println("Prezzo:" + viaggio.getPrice());
            System.out.println("Data di partenza:" + viaggio.getDataAnd());
            System.out.println("Data di ritorno:" + viaggio.getDataRit());
            System.out.println("Posti disponibili:" + viaggio.getAvailable());
            System.out.println("---------------------------------");
        }
        System.out.println("Inserire l'ID del viaggio che si vuole prenotare, 0 per tornare alla pagina precedente");
        Scanner reader = new Scanner(System.in);
        int n;
        while (true) {
            n = reader.nextInt();
            if (n > viaggi.size()) {
                System.out.println("inserire un numero valido");
            }
            else if(n==0){
                login.start();
            }
            BookBean bookBean = new BookBean(currentUser.getUsername(), n);


            try {
                bookTripController.bookTrip(bookBean);
                System.out.println("Prenotazione avvenuta con successo");
                break;
            } catch (PlacesTerminatedException | AlreadyPrenotedException e) {
                System.out.println(e.getMessage());
                login.start();
            }
        }
    }
    }

