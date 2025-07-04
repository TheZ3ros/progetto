package viewseconda.agency;

import com.ispw.progetto.bean.AgencyBean;
import com.ispw.progetto.exception.NotValidCouponException;
import viewseconda.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeAgencyCLI {
    private final AgencyBean currentAgency;

    public HomeAgencyCLI(AgencyBean currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start() throws SQLException, IOException, NotValidCouponException {
        Scanner reader = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            Printer.printMessage("Scegliere l'operazione da eseguire");
            Printer.printMessage("1-Inserisci un nuovo viaggio");
            Printer.printMessage("2-Controlla stato dei viaggi");

            int n = reader.nextInt();
            reader.nextLine(); // per evitare problemi col nextInt()

            switch (n) {
                case 1:
                    TripCreationCLI tripCreationCLI = new TripCreationCLI(currentAgency);
                    tripCreationCLI.start();  // il flusso torna qui
                    break;
                case 2:
                    AgencyTripsCLI agencyTripsCLI = new AgencyTripsCLI(currentAgency);
                    agencyTripsCLI.start(); // flusso torna qui
                    break;

                default:
                    Printer.printMessage("Inserire un'opzione valida");
            }

            Printer.printMessage("Vuoi continuare? (S/N)");
            String risposta = reader.nextLine();
            continua = risposta.equalsIgnoreCase("s");
        }
    }
}
