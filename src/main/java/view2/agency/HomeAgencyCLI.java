package view2.agency;

import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.factory.BeanFactory;
import view2.Printer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeAgencyCLI {
    private final BeanFactory currentAgency;

    public HomeAgencyCLI(BeanFactory currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start() throws SQLException, IOException, NotValidCouponException {
        Printer.printMessage("Scegliere l'operazione da eseguire");
        Printer.printMessage("1-Inserisci un nuovo viaggio");
        Printer.printMessage("2-Controlla stato dei viaggi");
        Scanner reader = new Scanner(System.in);
        int n;
        boolean continua = true;
        while (continua) {
            n = reader.nextInt();
            switch (n) {
                case 1:
                    TripCreationCLI tripCreationCLI = new TripCreationCLI(currentAgency);
                    tripCreationCLI.start(this);
                    break;
                case 2:
                    AgencyTripsCLI agencyTripsCLI = new AgencyTripsCLI(currentAgency);
                    agencyTripsCLI.start(this);
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

