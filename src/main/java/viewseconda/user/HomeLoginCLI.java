package viewseconda.user;

import com.ispw.progetto.bean.UserBean;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeLoginCLI implements UserHomeNavigator {
    private final UserBean currentUser;

    public HomeLoginCLI(UserBean currentUser) {
        this.currentUser = currentUser;
    }

    public void start() throws SQLException, IOException {
        Scanner reader = new Scanner(System.in);
        boolean continua = true;
        while (continua) {
            Printer.printMessage("Scegliere l'operazione da eseguire");
            Printer.printMessage("1-Prenota un nuovo viaggio");
            Printer.printMessage("2-Visualizza stato dei viaggi prenotati");
            Printer.printMessage("3-Visualizza informazioni account");

            int n = reader.nextInt();
            switch (n) {
                case 1 -> new TripViewCLI(currentUser).viewtrip(this);
                case 2 -> new BookedTripCLI(currentUser).start(this);
                case 3 -> new InfoUserCLI(currentUser).start(this);
                default -> Printer.printMessage("inserire un'opzione valida");
            }

            Printer.printMessage("Vuoi continuare? (S/N)");
            String risposta = reader.next();
            continua = risposta.equalsIgnoreCase("s");
        }
    }

    @Override
    public void goToHome() {
        try {
            start();
        } catch (Exception e) {
            Printer.printMessage("Errore nel tornare alla home: " + e.getMessage());
        }
    }
}
