package view2.agency;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.Factory.BeanFactory;
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
        while (true) {
            n = reader.nextInt();
            switch (n) {
                case 1:
                    TripCreationCLI tripCreationCLI = new TripCreationCLI(currentAgency);
                    tripCreationCLI.start(this);
                case 2:
                    AgencyTripsCLI agencyTripsCLI = new AgencyTripsCLI(currentAgency);
                    agencyTripsCLI.start(this);
                default:
                    Printer.printMessage("inserire un'opzione valida");
            }
        }

    }

    }

