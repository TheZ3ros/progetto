package view2.user;

import com.example.progetto.exception.AlreadyPrenotedException;
import com.example.progetto.exception.PlacesTerminatedException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeLoginCLI {
    private final BeanFactory currentUser;


    public HomeLoginCLI(BeanFactory currentUser) {
        this.currentUser = currentUser;
    }
    public void start() throws SQLException, IOException, PlacesTerminatedException, AlreadyPrenotedException {
        Printer.printMessage("Scegliere l'operazione da eseguire");
        Printer.printMessage("1-Prenota un nuovo viaggio");
        Printer.printMessage("2-Visualizza stato dei viaggi prenotati");
        Scanner reader = new Scanner(System.in);
        int n;
        boolean continua = true;
        while (continua) {
            n = reader.nextInt();
            switch (n) {
                case 1:
                    TripViewCLI tripViewCLI=new TripViewCLI(currentUser);
                    tripViewCLI.viewtrip(this);
                    break;
                case 2:
                    BookedTripCLI bookedTripCLI=new BookedTripCLI(currentUser);
                    bookedTripCLI.start(this);
                    break;
                default:
                    Printer.printMessage("inserire un'opzione valida");
            }
            Printer.printMessage("Vuoi continuare? (S/N)");
            String risposta = reader.next();
            continua = risposta.equalsIgnoreCase("s");
        }
    }
}
