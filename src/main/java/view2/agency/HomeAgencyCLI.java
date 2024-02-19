package view2.agency;

import com.example.progetto.bean.AgencyBean;
import com.example.progetto.exception.NotValidCouponException;
import com.example.progetto.pattern.Factory.BeanFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeAgencyCLI {
    private final BeanFactory currentAgency;

    public HomeAgencyCLI(BeanFactory currentAgency) {
        this.currentAgency = currentAgency;
    }

    public void start() throws SQLException, IOException, NotValidCouponException {
        System.out.println("Scegliere l'operazione da eseguire");
        System.out.println("1-Inserisci un nuovo viaggio");
        System.out.println("2-Controlla stato dei viaggi");
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
                    System.out.println("inserire un'opzione valida");
            }
        }

    }

    }

